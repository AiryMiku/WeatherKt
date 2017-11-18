package com.airy.weatherkt

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.airy.weatherkt.gson.Weather
import com.airy.weatherkt.util.HttpUtil
import com.airy.weatherkt.util.Utility
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.aqi.*
import kotlinx.android.synthetic.main.daily_forecast.*
import kotlinx.android.synthetic.main.hourly_forecast.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.suggestion.*
import kotlinx.android.synthetic.main.title.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_weather)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val weatherString = preferences.getString("weather", null)
        var mWeatherId:String
        if (weatherString != null) {
            //有缓存
            val weather = Utility.handleWeatherResponse(weatherString)
            mWeatherId = weather!!.basic!!.id!!
            showWeatherInfo(weather)
        } else {
            //无缓存
            mWeatherId = intent.getStringExtra("weather_id")
            weather_layout.visibility = View.INVISIBLE
            requestWeather(mWeatherId)
        }

        swipe_refresh.setOnRefreshListener({requestWeather(mWeatherId)})

        val bingPic = preferences.getString("bing_pic", null)
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bing_pic_img)
        } else {
            loadBingPic()
        }

    }


    //根据id请求城市天气
    fun requestWeather(weatherId: String) {
        //我的api
        val weatherUrl = "https://free-api.heweather.com/v5/weather?city=$weatherId&key=65154046739f4cd08f5d691bfcbd1c65"
        HttpUtil.get(weatherUrl, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@WeatherActivity, "获取天气信息扑街", Toast.LENGTH_SHORT).show()
                    swipe_refresh.isRefreshing = false
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseText = response.body().string()
                val weather = Utility.handleWeatherResponse(responseText)
                runOnUiThread {
                    if (weather != null && "ok" == weather!!.status) {
                        val editor = PreferenceManager.getDefaultSharedPreferences(this@WeatherActivity).edit()
                        editor.putString("weather", responseText)
                        editor.apply()
                        showWeatherInfo(weather)
                    } else {
                        Toast.makeText(this@WeatherActivity, "获取天气信息扑街", Toast.LENGTH_SHORT).show()
                    }
                    swipe_refresh.isRefreshing = false
                }
            }
        })
        loadBingPic()
    }

    //处理展示天气数据
    //还需要再添加，新的api
    private fun showWeatherInfo(weather: Weather?) =
            if (weather != null && "ok" == weather!!.status) {
                val cityName = weather!!.basic!!.city
                val updateTime = weather!!.basic!!.update!!.loc
                val degree = weather!!.now!!.tmp + "°C"
                val weatherInfo = weather!!.now!!.cond!!.txt
                title_city.text = cityName
                title_update_time.text = updateTime
                degree_text.text = degree
                weather_info_text.text = weatherInfo
                daily_forecast_layout.removeAllViews()
                hourly_forecast_layout.removeAllViews()
                for (dailyForecast in weather!!.daily_forecast!!) {
                    val view = LayoutInflater.from(this).inflate(R.layout.daily_forecast_item, daily_forecast_layout, false)
                    (view.findViewById<TextView>(R.id.date_text) as TextView).text = dailyForecast.date!!.split("-")[2] + "日"
                    (view.findViewById<TextView>(R.id.info_text) as TextView).text = dailyForecast.cond!!.txt_d + " " + dailyForecast.cond!!.txt_n
                    (view.findViewById<TextView>(R.id.max_text) as TextView).text = dailyForecast.tmp!!.max
                    (view.findViewById<TextView>(R.id.min_text) as TextView).text = dailyForecast.tmp!!.min
                    daily_forecast_layout.addView(view)
                }
                if (weather!!.hourly_forecast!!.isNotEmpty()) {
                    for (hourlyForecast in weather!!.hourly_forecast!!) {
                        val view = LayoutInflater.from(this).inflate(R.layout.hourly_forecast_item, hourly_forecast_layout, false)
                        (view.findViewById<TextView>(R.id.hourly_text) as TextView).text = hourlyForecast.date!!.split(" ")[1]
                        (view.findViewById<TextView>(R.id.hourly_cond_text) as TextView).text = hourlyForecast.cond!!.txt
                        (view.findViewById<TextView>(R.id.hourly_tmp_text) as TextView).text = hourlyForecast.tmp + "°C"
                        (view.findViewById<TextView>(R.id.hourly_wind_dir_text) as TextView).text = hourlyForecast.wind!!.dir
                        (view.findViewById<TextView>(R.id.hourly_wind_sc_text) as TextView).text = hourlyForecast.wind!!.sc
                        hourly_forecast_layout.addView(view)
                    }
                } else {
                    hourly_forecast_layout.visibility = View.INVISIBLE
                }
                if (weather!!.aqi != null) {
                    aqi_text.text = weather!!.aqi!!.city!!.aqi
                    pm25_text.text  = weather!!.aqi!!.city!!.pm25
                }
                val comfort = "舒适度：" + weather.suggestion!!.comf!!.txt
                val carWash = "洗车指数：" + weather.suggestion!!.cw!!.txt
                val sport = "运动建议：" + weather.suggestion!!.sport!!.txt
                comfort_text.text = comfort
                car_wash_text.text = carWash
                sport_text.text = sport
                weather_layout.visibility = View.VISIBLE
    //            val i = Intent(this, AutoUpdateService::class.java)
    //            startService(i)
            } else {
                Toast.makeText(this@WeatherActivity, "获取天气信息扑街", Toast.LENGTH_SHORT).show()
            }

    //load pic
    private fun loadBingPic() {
        val requestBingPic = "http://guolin.tech/api/bing_pic"
        HttpUtil.get(requestBingPic, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val bingPic = response.body().string()
                val editor = PreferenceManager.getDefaultSharedPreferences(this@WeatherActivity).edit()
                editor.putString("bing_pic", bingPic)
                editor.apply()
                runOnUiThread { Glide.with(this@WeatherActivity).load(bingPic).into(bing_pic_img) }
            }
        })
    }
}
