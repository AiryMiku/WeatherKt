package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class HourlyForecast {

    /**
     * cond : {"code":"300","txt":"阵雨"}
     * date : 2017-04-18 16:00
     * hum : 61
     * pop : 95
     * pres : 1008
     * tmp : 27
     * wind : {"deg":"320","dir":"西北风","sc":"微风","spd":"6"}
     */

    var cond: Cond? = null
    var date: String? = null
    var hum: String? = null
    var pop: String? = null
    var pres: String? = null
    var tmp: String? = null
    var wind: Wind? = null

    class Cond {
        /**
         * code : 300
         * txt : 阵雨
         */

        var code: String? = null
        var txt: String? = null
    }

    class Wind {
        /**
         * deg : 320
         * dir : 西北风
         * sc : 微风
         * spd : 6
         */

        var deg: String? = null
        var dir: String? = null
        var sc: String? = null
        var spd: String? = null
    }


}