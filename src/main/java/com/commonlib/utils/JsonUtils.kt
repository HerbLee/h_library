package com.aialias.hlibraries.utils

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type


/**
 *
 * @author HERB
 * @date Created on 2018/2/24 6:26
 * @function:json utils
 */
object JsonUtils {


    /**
     * 获取数据
     */
    fun getJsonObject(json: String): JSONObject? {
        try {
            val jsonObject = JSONObject(json)
            return jsonObject.getJSONObject("data")
        }catch (e:Exception){
            com.orhanobut.logger.Logger.e("解析错误",e)
        }
        return null


    }
    fun getJsonObject(json: String,code:String): JSONObject? {
        try {
            val jsonObject = JSONObject(json)
            return jsonObject.getJSONObject(code)
        }catch (e:Exception){
            com.orhanobut.logger.Logger.e("解析错误",e)
        }
        return null

    }
    fun getcode(json: String): String {
        val jsonObject = JSONObject(json)
        return jsonObject.getString("code")

    }

    /**
     * 获取数据
     */
    fun getJsonArray(json: String): JSONArray? {
        val jsonObject = JSONObject(json)
        try {

            return jsonObject.getJSONArray("data")
        }catch (e:Exception){
            com.orhanobut.logger.Logger.e("解析错误",e)
        }
        return null
    }
    fun getJsonArray(json: String, code:String): JSONArray? {
        val jsonObject = JSONObject(json)
        try {
            return jsonObject.getJSONArray(code)
        }catch (e:Exception){
            com.orhanobut.logger.Logger.e("解析错误",e)
        }
        return null
    }

    fun getJson(p1: Response?): String? {
        try {
            return p1!!.body()!!.string()
        }catch (e:Exception){
            Logger.e("{}出现异常","解析",e)
        }
        return null
    }


    /**
     * 将对象转换成json字符串
     * @param object
     * @return
     */
    fun obj2Json(`object`: Any): String {
        val gs = Gson()
        return gs.toJson(`object`)
    }

    /**
     * 将字符串转换成json对象
     * @return
     */
    fun <T> json2Obj(json: String, tClass: Class<T>): T? {
        try {
            val gs = Gson()
            return gs.fromJson(json, tClass as Type) as T
        } catch (e: Exception) {
            com.orhanobut.logger.Logger.e("解析错误",e)
        }

        return null
    }

    fun isSuccess(json: String?, code: String):Boolean {
        try {
            val jsonObject = JSONObject(json)
            val code1 = jsonObject.getString("code")
            if (code1 == code) {
                return true
            }
        }catch (e:Exception){
            Logger.e("{}解析错误","返回的code",e)
        }

        return false
    }

    fun getMsg(js: String?, s: String,def: String): String {
        try {
            val jsonObject = JSONObject(js)
            return jsonObject.getString(s)
        }catch (e:Exception){

        }
        return def
    }

}