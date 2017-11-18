package com.airy.weatherkt

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.airy.weatherkt.db.City
import com.airy.weatherkt.db.County
import com.airy.weatherkt.db.Province
import com.airy.weatherkt.util.HttpUtil
import com.airy.weatherkt.util.Utility
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.choose_area.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.litepal.crud.DataSupport
import java.io.IOException

/**
 * Created by Airy on 2017/11/18.
 */
class ChooseAreaFragment:Fragment(){
    val LEVEL_PROVINCE = 0
    val LEVEL_CITY = 1
    val LEVEL_COUNTY = 2

    private var progressBar: ProgressBar? = null
    lateinit var adapter: ArrayAdapter<String>
    internal var dataList: MutableList<String> = ArrayList()
    lateinit var provinceList: List<Province>
    lateinit var cityList: List<City>
    lateinit var countyList: List<County>
    lateinit var selectedProvince: Province
    lateinit var list_view: ListView
    lateinit var selectedCity: City
    var currentLevel: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.choose_area,container,false)
        adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,dataList)
        list_view = view.findViewById(R.id.list_view)
        list_view.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_view.setOnItemClickListener { parent, view, position, id ->
            if (currentLevel == LEVEL_PROVINCE) {
                selectedProvince = provinceList[position]
                queryCities()
            } else if (currentLevel == LEVEL_CITY) {
                selectedCity = cityList[position]
                queryCounties()
            } else if (currentLevel == LEVEL_COUNTY) {
                val weatherId = countyList[position].weatherId
                if (activity is MainActivity) {
                    val intent = Intent(activity, WeatherActivity::class.java)
                    intent.putExtra("weather_id", weatherId)
                    startActivity(intent)
                    activity.finish()
                } else if (activity is WeatherActivity) {
                    val activity = activity as WeatherActivity
                    activity.drawer_layout.closeDrawers()
                    activity.swipe_refresh.isRefreshing = true
                    activity.requestWeather(weatherId)
                }

            }
        }
        back_button.setOnClickListener({
            if (currentLevel == LEVEL_COUNTY) {
                queryCities()
            } else if (currentLevel == LEVEL_CITY) {
                queryProvinces()
            }
        })

        queryProvinces()
    }

    private fun queryProvinces(){
        title_text.text = "天朝"
        back_button.visibility = View.GONE
        provinceList = DataSupport.findAll(Province::class.java)
        if (provinceList.isNotEmpty()) {
            dataList.clear()
            for (province in provinceList) {
                dataList.add(province.provinceName)
            }
            adapter.notifyDataSetChanged()
            list_view.setSelection(0)
            currentLevel = LEVEL_PROVINCE
        } else {
            val address = "http://guolin.tech/api/china"
            queryFromServer(address,"province")
        }
    }

    //查询省内的市，优先数据库，没有则服务器查询
    private fun queryCities() {
        title_text.text = selectedProvince.provinceName
        back_button.visibility = View.VISIBLE
        cityList = DataSupport.where("proviceid = ?", selectedProvince.id.toString()).find(City::class.java)
        if (cityList.isNotEmpty()) {
            dataList.clear()
            for (city in cityList) {
                dataList.add(city.cityName)
            }
            adapter.notifyDataSetChanged()
            list_view.setSelection(0)
            currentLevel = LEVEL_CITY
        } else {
            val provinceCode = selectedProvince.provinceCode
            val address = "http://guolin.tech/api/china/" + provinceCode
            queryFromServer(address, "city")
        }
    }

    //查询室内的县，优先数据库，没有则服务器查询
    private fun queryCounties() {
        title_text.text = selectedCity.cityName
        back_button.visibility = View.VISIBLE
        countyList = DataSupport.where("cityid = ?", selectedCity.id.toString()).find(County::class.java)
        if (countyList.isNotEmpty()) {
            dataList.clear()
            for (county in countyList) {
                dataList.add(county.countyName)
            }
            adapter.notifyDataSetChanged()
            list_view.setSelection(0)
            currentLevel = LEVEL_COUNTY
        } else {
            val provinceCode = selectedProvince.provinceCode
            val cityCode = selectedCity.cityCode
            val address = "http://guolin.tech/api/china/$provinceCode/$cityCode"
            queryFromServer(address, "county")
        }
    }

    private fun queryFromServer(address: String,type: String){
        showProgressDialog()
        HttpUtil.get(address,object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                    closeProgressDialog()
                    Toast.makeText(context,"扑街了",Toast.LENGTH_SHORT).show()
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response?) {
                val responseText = response?.body()!!.string()
                var result = false
                when (type) {
                    "province" -> result = Utility.handleProvinceResponse(responseText)
                    "city" -> result = Utility.handleCityResponse(responseText,selectedProvince.id)
                    "county" -> result = Utility.handleCountyResponse(responseText,selectedCity.id)
                }
                if (result) {
                    activity.runOnUiThread {
                        closeProgressDialog()
                        when(type) {
                            "province" -> queryProvinces()
                            "city" -> queryCities()
                            "county" -> queryCounties()
                        }
                    }
                }


            }
        })

    }

    //进度对话框
    private fun showProgressDialog() {
        if (progressBar == null) {
            progressBar = ProgressBar(context)
        }
        Log.d("calldialog", "showProgressDialog: ")
    }

    //关闭进度对话框
    private fun closeProgressDialog() {
//        if (progressBar != null) {
//            progressBar!!.
//        }
        Log.d("dismiss", "closeProgressDialog: ")
    }

}