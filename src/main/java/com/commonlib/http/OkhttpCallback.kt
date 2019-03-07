package com.commonlib.http

import okhttp3.Call
import okhttp3.Response
import java.io.IOException

/**
 *
 * @author HERB
 * @date Created on 2018/2/24 21:18
 * @function: 回调接口
 */
interface OkhttpCallback {
    fun onResponse(call: Call?, response: Response?)

    fun onFailure(call: Call?, e: IOException?)
}