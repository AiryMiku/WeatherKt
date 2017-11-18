package com.airy.weatherkt.gson

/**
 * Created by Airy on 2017/11/17.
 */
class Basic{

    var city: String? = null
    var cnty: String? = null
    var id: String? = null
    var lat: String? = null
    var lon: String? = null
    var update: Update? = null

    class Update {

        var loc: String? = null
        var utc: String? = null
    }
}