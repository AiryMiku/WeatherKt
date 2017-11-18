package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class DailyForecast{

    /**
     * astro : {"mr":"null","ms":"11:06","sr":"06:03","ss":"18:48"}
     * cond : {"code_d":"104","code_n":"300","txt_d":"阴","txt_n":"阵雨"}
     * date : 2017-04-18
     * hum : 72
     * pcpn : 1.2
     * pop : 96
     * pres : 1010
     * tmp : {"max":"32","min":"23"}
     * uv : 6
     * vis : 18
     * wind : {"deg":"255","dir":"无持续风向","sc":"微风","spd":"3"}
     */

    var astro: Astro? = null
    var cond: CondX? = null
    var date: String? = null
    var hum: String? = null
    var pcpn: String? = null
    var pop: String? = null
    var pres: String? = null
    var tmp: Tmp? = null
    var uv: String? = null
    var vis: String? = null
    var wind: WindX? = null

    class Astro {
        /**
         * mr : null
         * ms : 11:06
         * sr : 06:03
         * ss : 18:48
         */

        var mr: String? = null
        var ms: String? = null
        var sr: String? = null
        var ss: String? = null
    }

    class CondX {
        /**
         * code_d : 104
         * code_n : 300
         * txt_d : 阴
         * txt_n : 阵雨
         */

        var code_d: String? = null
        var code_n: String? = null
        var txt_d: String? = null
        var txt_n: String? = null
    }

    class Tmp {
        /**
         * max : 32
         * min : 23
         */

        var max: String? = null
        var min: String? = null
    }

    class WindX {
        /**
         * deg : 255
         * dir : 无持续风向
         * sc : 微风
         * spd : 3
         */

        var deg: String? = null
        var dir: String? = null
        var sc: String? = null
        var spd: String? = null
    }

}