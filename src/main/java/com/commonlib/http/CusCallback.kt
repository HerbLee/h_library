package com.commonlib.http

/**
 * @des: 用户网络访问回掉接口
 * @author: HerbLee
 * @data: 2018/8/9 上午9:42
 */
interface CusCallback<T> {
    
    fun onSuccess(t: T)
    
    fun onError(status: String)

    fun onException(e: Exception?)
}