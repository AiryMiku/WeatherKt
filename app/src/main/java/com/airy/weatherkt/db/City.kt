package com.airy.weatherkt.db

import org.litepal.crud.DataSupport

/**
 * Created by Airy on 2017/11/17.
 */
class City: DataSupport() {

    internal var id:Int = 0
    var cityName:String = ""
    var cityCode:Int = 0
    var proviceId:Int = 0

}