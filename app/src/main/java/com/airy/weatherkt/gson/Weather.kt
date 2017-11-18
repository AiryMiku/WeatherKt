package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class Weather {
    /**
     * aqi : {"city":{"aqi":"61","co":"1","no2":"40","o3":"159","pm10":"72","pm25":"42","qlty":"良","so2":"18"}}
     * basic : {"city":"广州","cnty":"中国","id":"CN101280101","lat":"23.125178","lon":"113.280637","update":{"loc":"2017-04-18 15:51","utc":"2017-04-18 07:51"}}
     * daily_forecast : [{"astro":{"mr":"null","ms":"11:06","sr":"06:03","ss":"18:48"},"cond":{"code_d":"104","code_n":"300","txt_d":"阴","txt_n":"阵雨"},"date":"2017-04-18","hum":"72","pcpn":"1.2","pop":"96","pres":"1010","tmp":{"max":"32","min":"23"},"uv":"6","vis":"18","wind":{"deg":"255","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"mr":"00:40","ms":"11:56","sr":"06:02","ss":"18:49"},"cond":{"code_d":"300","code_n":"306","txt_d":"阵雨","txt_n":"中雨"},"date":"2017-04-19","hum":"71","pcpn":"0.0","pop":"88","pres":"1010","tmp":{"max":"30","min":"23"},"uv":"5","vis":"18","wind":{"deg":"143","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"mr":"01:26","ms":"12:48","sr":"06:01","ss":"18:49"},"cond":{"code_d":"306","code_n":"306","txt_d":"中雨","txt_n":"中雨"},"date":"2017-04-20","hum":"74","pcpn":"8.0","pop":"100","pres":"1011","tmp":{"max":"27","min":"20"},"uv":"6","vis":"17","wind":{"deg":"149","dir":"无持续风向","sc":"微风","spd":"3"}}]
     * hourly_forecast : [{"cond":{"code":"300","txt":"阵雨"},"date":"2017-04-18 16:00","hum":"61","pop":"95","pres":"1008","tmp":"27","wind":{"deg":"320","dir":"西北风","sc":"微风","spd":"6"}},{"cond":{"code":"305","txt":"小雨"},"date":"2017-04-18 19:00","hum":"68","pop":"78","pres":"1008","tmp":"27","wind":{"deg":"131","dir":"东南风","sc":"微风","spd":"9"}},{"cond":{"code":"305","txt":"小雨"},"date":"2017-04-18 22:00","hum":"77","pop":"49","pres":"1010","tmp":"24","wind":{"deg":"80","dir":"东风","sc":"微风","spd":"9"}}]
     * now : {"cond":{"code":"104","txt":"阴"},"fl":"34","hum":"62","pcpn":"0","pres":"1007","tmp":"28","vis":"7","wind":{"deg":"250","dir":"西风","sc":"微风","spd":"7"}}
     * status : ok
     * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"很不舒适","txt":"白天天气晴好，但烈日炎炎和较大的空气湿度会使您感到很闷热，很不舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},"trav":{"brf":"适宜","txt":"天气较好，感觉有些热，总体来说还是好天气哦。适宜旅游，可不要错过机会呦"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    var aqi: Aqi? = null
    var basic: Basic? = null
    var now: Now? = null
    var status: String? = null
    var suggestion: Suggestion? = null
    var daily_forecast: List<DailyForecast>? = null
    var hourly_forecast: List<HourlyForecast>? = null

}