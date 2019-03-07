package com.commonlib.http

import com.orhanobut.logger.Logger
import okhttp3.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 *
 * @author HERB
 * @date Created on 2018/2/23 16:29
 * @function: 网络访问类
 */
class HttpClient private constructor() {
    //懒汉模式私有化构造
    companion object {
        val instance: HttpClient by lazy { HttpClient() }
    }

    private val UTF_8 = "UTF-8"

    private val builder = OkHttpClient.Builder()
    private var client = builder.build()


    /**
     * okhttp的get请求
     *
     * @param url            服务器地址
     * @param requestHeader  请求头
     * @param param          请求的附加数据
     * @param netCallback 返回的数据
     */
    fun get(url: String, requestHeader: Map<String, String>?, param: Map<String, String>?, netCallback: OkhttpCallback) {
        var url = url

        if (param != null && param.isNotEmpty()) {
            url = url + "?" + mapToQueryString(param)
        }

        val builder = Request.Builder()

        if (requestHeader != null) {
            for (ss in requestHeader.keys) {
                builder.addHeader(toStrings(ss), toStrings(requestHeader[ss]))
            }
        }
        try {
            val request = builder.url(toStrings(url)).build()

            client!!.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    netCallback.onFailure(call, e)
                }

                override fun onResponse(call: Call?, response: Response?) {
                    netCallback.onResponse(call, response)
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.e("net post error == " + e.toString())

        }

    }

    /**
     * 解析map集合
     *
     * @param map 要想服务器提交的数据
     * @return 整合后的字符串
     */
    private fun mapToQueryString(map: Map<String, String>): String {
        val string = StringBuilder()

        try {
            for ((key, value) in map) {
                string.append(key)
                string.append("=")
                string.append(URLEncoder.encode(value, UTF_8))
                string.append("&")
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            Logger.e("解析错误",e)
        }

        return string.toString()
    }

    /**
     * post请求
     *
     * @param url           服务器地址
     * @param requestHeader 请求头
     * @param param         post内容
     * @param netCallback   继承返回数据
     */
    fun post(url: String, requestHeader: Map<String, String>?, param: Map<String, String>?, netCallback: OkhttpCallback) {


        //要post的内容
        val builder = FormBody.Builder()
        if (param != null) {
            for (ss in param.keys) {
                builder.add(toStrings(ss), toStrings(param[ss]))
            }
        }
        val requestBody = builder.build()

        //Request封装call内容
        val builder1 = Request.Builder()
        if (requestHeader != null) {
            for (ss in requestHeader.keys) {
                builder1.addHeader(toStrings(ss), toStrings(requestHeader[ss]))
            }
        }
        try {
            val result = builder1.url(toStrings(url)).post(requestBody).build()

            //调用okhttp
            client.newCall(result).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    netCallback.onFailure(call, e)
                }

                override fun onResponse(call: Call?, response: Response?) {
                    netCallback.onResponse(call, response)
                }

            })
        } catch (e: Exception) {
            Logger.e("post链接数据错误" , e)
        }

    }

    /**
     * 预防空值问题
     * @param value
     * @return
     */
    private fun toStrings(value: String?): String {
        return value ?: " "
    }




}