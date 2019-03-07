package com.commonlib.utils

/**
 *create by echo
 */
class MessageEvent {
    private var key:String?=null
    private var value:Any?=null

    constructor(key: String?, value: Any?) {
        this.key = key
        this.value = value
    }

    constructor()

    fun getKey(): String? {
        return key
    }
    fun setKey(key:String){
        this.key = key
    }

    fun getValue():Any?{
        return value
    }

    fun setValue(v:Any){
        this.value = v
    }

}