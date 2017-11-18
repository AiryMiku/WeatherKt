package com.airy.weatherkt.gson

import java.io.Serializable

/**
 * Created by Airy on 2017/11/17.
 */
class Aqi:Serializable{

    public var city:City? = null

    class City{
        var aqi: String? = null
        var co: String? = null
        var no2: String? = null
        var o3: String? = null
        var pm10: String? = null
        var pm25: String? = null
        var qlty: String? = null
        var so2: String? = null
    }

}