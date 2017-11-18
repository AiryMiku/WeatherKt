package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class Suggestion {

    /**
     * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
     * comf : {"brf":"很不舒适","txt":"白天天气晴好，但烈日炎炎和较大的空气湿度会使您感到很闷热，很不舒适。"}
     * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
     * drsg : {"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}
     * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
     * sport : {"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"}
     * trav : {"brf":"适宜","txt":"天气较好，感觉有些热，总体来说还是好天气哦。适宜旅游，可不要错过机会呦"}
     * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
     */

    var air: Air? = null
    var comf: Comf? = null
    var cw: Cw? = null
    var drsg: Drsg? = null
    var flu: Flu? = null
    var sport: Sport? = null
    var trav: Trav? = null
    var uv: Uv? = null

    class Air {
        /**
         * brf : 中
         * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Comf {
        /**
         * brf : 很不舒适
         * txt : 白天天气晴好，但烈日炎炎和较大的空气湿度会使您感到很闷热，很不舒适。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Cw {
        /**
         * brf : 不宜
         * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Drsg {
        /**
         * brf : 热
         * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Flu {
        /**
         * brf : 少发
         * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Sport {
        /**
         * brf : 较适宜
         * txt : 阴天，较适宜进行各种户内外运动。
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Trav {
        /**
         * brf : 适宜
         * txt : 天气较好，感觉有些热，总体来说还是好天气哦。适宜旅游，可不要错过机会呦
         */

        var brf: String? = null
        var txt: String? = null
    }

    class Uv {
        /**
         * brf : 弱
         * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
         */

        var brf: String? = null
        var txt: String? = null
    }

}