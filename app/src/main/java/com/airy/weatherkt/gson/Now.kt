package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class Now {
    /**
     * cond : {"code":"104","txt":"阴"}
     * fl : 34
     * hum : 62
     * pcpn : 0
     * pres : 1007
     * tmp : 28
     * vis : 7
     * wind : {"deg":"250","dir":"西风","sc":"微风","spd":"7"}
     */

    var cond: Cond? = null
    var fl: String? = null
    var hum: String? = null
    var pcpn: String? = null
    var pres: String? = null
    var tmp: String? = null
    var vis: String? = null
    var wind: Wind? = null

    class Cond {
        /**
         * code : 104
         * txt : 阴
         */

        var code: String? = null
        var txt: String? = null
    }

    class Wind {
        /**
         * deg : 250
         * dir : 西风
         * sc : 微风
         * spd : 7
         */

        var deg: String? = null
        var dir: String? = null
        var sc: String? = null
        var spd: String? = null
    }
}