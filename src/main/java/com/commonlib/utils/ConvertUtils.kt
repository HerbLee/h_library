package com.aialias.hlibraries.utils

/**
 *
 * @author HERB
 * @date Created on 2018/2/12 20:49
 * @function:
 */
object ConvertUtils {

    /**
     * 16进制转化为数字
     * @param ch 16进制
     * @param index 索引
     * @return 转化结果
     * @throws Exception 转化失败异常
     */
    @Throws(Exception::class)
    private fun toDigit(ch: Char, index: Int): Int {
        val digit = Character.digit(ch, 16)
        if (digit == -1) {
            throw Exception("Illegal hexadecimal character " + ch
                    + " at index " + index)
        }
        return digit
    }

    /**
     * int转换为byte数组
     *
     * @param res
     * @return
     */
    fun intToByte(res: Int): ByteArray {
        val targets = ByteArray(4)
        targets[0] = (res and 0xff).toByte()// 最低位
        targets[1] = (res shr 8 and 0xff).toByte()// 次低位
        targets[2] = (res shr 16 and 0xff).toByte()// 次高位
        targets[3] = res.ushr(24).toByte()// 最高位,无符号右移。
        return targets
    }



    /**
     * 保留几位小数
     */
    fun saveDecimals(cnt: Int, value: Double): String {
        return if (cnt == 2)
            String.format("%.02f", value)
        else if (cnt == 1)
            String.format("%.01f", value)
        else
            String.format("%.0f", value)
    }

    /**
     * null转String
     * @param str
     * @return
     */
    fun nullOfString(str: String?): String {
        var str = str
        if (str == null) {
            str = ""
        }
        return str
    }

    /**
     * String转Byte
     * @param str
     * @return
     */
    fun stringToByte(str: String?): Byte {
        var b: Byte = 0
        if (str != null) {
            try {
                b = java.lang.Byte.parseByte(str)
            } catch (e: Exception) {

            }

        }
        return b
    }

    /**
     * String转Boolean
     * @param str
     * @return
     */
    fun stringToBoolean(str: String?): Boolean {
        return if (str == null) {
            false
        } else {
            if (str == "1") {
                true
            } else if (str == "0") {
                false
            } else {
                try {
                    java.lang.Boolean.parseBoolean(str)
                } catch (e: Exception) {
                    false
                }

            }
        }
    }

    /**
     * String转Int
     * @param str
     * @return
     */
    fun stringToInt(str: String?): Int {
        var i = 0
        if (str != null) {
            try {
                i = Integer.parseInt(str.trim { it <= ' ' })
            } catch (e: Exception) {
                i = 0
            }

        } else {
            i = 0
        }
        return i
    }

    /**
     * String转Short
     * @param str
     * @return
     */
    fun stringToShort(str: String?): Short {
        var i: Short = 0
        if (str != null) {
            try {
                i = java.lang.Short.parseShort(str.trim { it <= ' ' })
            } catch (e: Exception) {
                i = 0
            }

        } else {
            i = 0
        }
        return i
    }

    /**
     * String转Double
     * @param str
     * @return
     */
    fun stringToDouble(str: String?): Double {
        var i = 0.0
        if (str != null) {
            try {
                i = java.lang.Double.parseDouble(str.trim { it <= ' ' })
            } catch (e: Exception) {
                i = 0.0
            }

        } else {
            i = 0.0
        }
        return i
    }

    /**
     * Int转String
     * @param i
     * @return
     */
    fun intToString(i: Int): String {
        var str = ""
        try {
            str = i.toString()
        } catch (e: Exception) {
            str = ""
        }

        return str
    }

    /**
     * Double转Long
     * @param d
     * @return
     */
    fun doubleToLong(d: Double): Long {
        var lo: Long = 0
        try {
            //double转换成long前要过滤掉double类型小数点后数据
            lo = java.lang.Long.parseLong(d.toString().substring(0, d.toString().lastIndexOf(".")))
        } catch (e: Exception) {
            lo = 0
        }

        return lo
    }

    /**
     * Double转Int
     * @param d
     * @return
     */
    fun doubleToInt(d: Double): Int {
        var i = 0
        try {
            //double转换成long前要过滤掉double类型小数点后数据
            i = Integer.parseInt(d.toString().substring(0, d.toString().lastIndexOf(".")))
        } catch (e: Exception) {
            i = 0
        }

        return i
    }

    /**
     * Long转Double
     * @param d
     * @return
     */
    fun longToDouble(d: Long): Double {
        var lo = 0.0
        try {
            lo = java.lang.Double.parseDouble(d.toString())
        } catch (e: Exception) {
            lo = 0.0
        }

        return lo
    }

    /**
     * Long转Int
     * @param d
     * @return
     */
    fun longToInt(d: Long): Int {
        var lo = 0
        try {
            lo = Integer.parseInt(d.toString())
        } catch (e: Exception) {
            lo = 0
        }

        return lo
    }

    /**
     * String转Long
     * @param str
     * @return
     */
    fun stringToLong(str: String): Long {
        var li: Long? = 0
        try {
            li = java.lang.Long.valueOf(str)
        } catch (e: Exception) {
            //li = new Long(0);
        }

        return li!!.toLong()
    }

    /**
     * Long转String
     * @param li
     * @return
     */
    fun longToString(li: Long): String {
        var str = ""
        try {
            str = li.toString()
        } catch (e: Exception) {

        }

        return str
    }
}