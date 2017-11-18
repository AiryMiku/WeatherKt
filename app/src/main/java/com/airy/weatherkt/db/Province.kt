package com.airy.weatherkt.db

import org.litepal.crud.DataSupport

/**
 * Created by Airy on 2017/11/17.
 */
class Province: DataSupport(){

    internal var id:Int = 0
    internal var provinceName:String = ""
    internal var provinceCode:Int = 0

}