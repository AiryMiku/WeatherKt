package com.airy.weatherkt.util

import android.text.TextUtils
import com.airy.weatherkt.db.City
import com.airy.weatherkt.db.County
import com.airy.weatherkt.db.Province
import com.airy.weatherkt.gson.Weather
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Airy on 2017/11/17.
 */
class Utility {

    companion object {
        fun handleProvinceResponse(response: String):Boolean {
            if (!TextUtils.isEmpty(response)) {
                try {
                   val allProvince: JSONArray = JSONArray(response)
                   for (i in 0 until allProvince.length()){
                       val provinceObject = allProvince.getJSONObject(i)
                       val province = Province()
                       province.provinceName = provinceObject.getString("name")
                       province.provinceCode = provinceObject.getInt("id")
                       province.save()
                   }
                    return true
                }catch (e:JSONException){
                    e.printStackTrace()
                }
            }

            return false
        }

        fun handleCityResponse(response: String,provinceId: Int):Boolean{
            if (!TextUtils.isEmpty(response)){
                try {
                    val allCities:JSONArray = JSONArray(response)
                    for (i in 0 until allCities.length()) {
                        val cityObejct: JSONObject = allCities.getJSONObject(i)
                        val city: City = City()
                        city.cityName = cityObejct.getString("name")
                        city.cityCode = cityObejct.getInt("id")
                        city.proviceId = provinceId
                        city.save()
                    }
                    return true
                }catch (e:JSONException){
                    e.printStackTrace()
                }
            }
            return false
        }

        fun handleCountyResponse(response: String,cityId:Int):Boolean{
            if (!TextUtils.isEmpty(response)){
                try {
                    val allCounties = JSONArray(response)
                    for (i in 0 until allCounties.length()) {
                        val countyObject = allCounties.getJSONObject(i)
                        val county = County()
                        county.countyName = countyObject.getString("name")
                        county.weatherId = countyObject.getString("weather_id")
                        county.cityId = cityId
                        county.save()
                    }
                }catch (e:JSONException){
                    e.printStackTrace()
                }
                return true
            }
            return false
        }

        fun handleWeatherResponse(response: String):Weather?{
            try {
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("HeWeather5")
                val weatherContent = jsonArray.getJSONObject(0).toString()
                return Gson().fromJson<Weather>(weatherContent, Weather::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

    }

}