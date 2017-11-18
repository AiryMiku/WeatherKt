package com.airy.weatherkt.db

import org.litepal.crud.DataSupport

/**
 * Created by Airy on 2017/11/17.
 */
class County: DataSupport(){

    internal var id:Int = 0
    internal var countyName:String = ""
    internal var weatherId:String = ""
    internal var cityId:Int = 0

}