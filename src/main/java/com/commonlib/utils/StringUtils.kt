package com.aialias.hlibraries.utils

import java.security.MessageDigest

/**
 *
 * @author HERB
 * @date Created on 2018/2/14 11:24
 * @function:
 */
object StringUtils {
    /**
     * 根据级联菜单格式化地址
     * @param p0 一级目录
     * @param p1 二级
     * @param p2 三级
     * @param p3 四级
     */
    fun formatAddress(p0:String,p1:String,p2:String,p3:String):String{
        var result = ""
        if (isEmpt(p0)){
           result += p0+"(省/市)"
        }
        if (isEmpt(p1)){
            result += p1
        }
        if (isEmpt(p2)){
            result += p2
        }
        if (isEmpt(p3)){
            result += p3
        }
        return result
    }
    /**
     * 判断字符串是否为空
     * @return boolean
     */
    fun isEmpt(str:String?):Boolean{
        if (str == null || str == "null" || str== "" || str.isEmpty()){
            return true
        }
        return false
    }
    fun formatString(str:String):String{
        if (str!=null && str !="null"){
            return str
        }
        return ""
    }


    fun MD5(inStr: String): String {
        var md5: MessageDigest? = null
        try {
            md5 = MessageDigest.getInstance("MD5")
        } catch (e: Exception) {
            println(e.toString())
            e.printStackTrace()
            return ""
        }

        val charArray = inStr.toCharArray()
        val byteArray = ByteArray(charArray.size)

        for (i in charArray.indices)
            byteArray[i] = charArray[i].toByte()

        val md5Bytes = md5!!.digest(byteArray)

        val hexValue = StringBuffer()

        for (i in md5Bytes.indices) {
            val `val` = md5Bytes[i].toInt() and 0xff
            if (`val` < 16)
                hexValue.append("0")
            hexValue.append(Integer.toHexString(`val`))
        }

        return hexValue.toString()
    }

    fun formatDate(time:String):String{
        val replace = time.replace("T", " ")
        val length = replace.length
        var result = ""
        result = try {
            replace.substring(0,length-4)
        }catch (e: Exception){
            "格式错误"
        }

        return result
    }

    fun obj2Int(str: Any):Int{
        if (str == null){
            return 0
        }
        return try {
            str.toString().toInt()
        }catch (e:Exception){
            0
        }
    }
    fun obj2Int(str: Any, def:Int ):Int{
        if (str == null){
            return def
        }
        return try {
            str.toString().toInt()
        }catch (e:Exception){
            def
        }
    }



}