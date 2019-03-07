package com.aialias.hlibraries.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author HERB
 * @date Created on 2018/2/12 20:44
 * @function:
 */
object AppDateMgr {

    val YYYYMMDD_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val HHMMSS_FORMAT = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val YYYYMMDDHHMMSS_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val CHINESE_ZODIAC = arrayOf("猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊")
    private val ZODIAC = arrayOf("水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座")

    private val ZODIAC_FLAGS = intArrayOf(20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22)

    /**
     * 当天的年月日
     * @return
     */
    fun todayYyyyMmDd(): String {
        return YYYYMMDD_FORMAT.format(Date())
    }

    /**
     * 当天的时分秒
     * @return
     */
    fun todayHhMmSs(): String {
        return HHMMSS_FORMAT.format(Date())
    }

    /**
     * 当天的年月日时分秒
     * @return
     */
    fun todayYyyyMmDdHhMmSs(): String {
        return YYYYMMDDHHMMSS_FORMAT.format(Date())
    }


    /**
     * 获取年月日
     * @param dateTime
     * @return
     */
    fun parseYyyyMmDd(dateTime: String): String {
        var result = ""

        try {
            val e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime)
            result = YYYYMMDD_FORMAT.format(e)
        } catch (var3: ParseException) {
            var3.printStackTrace()
        }

        return result
    }

    /**
     * 获取年月日
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    fun parseYyyyMmDd(dateTime: String, simpleDateFormat: SimpleDateFormat): String {
        var result = ""

        try {
            val e = simpleDateFormat.parse(dateTime)
            result = YYYYMMDD_FORMAT.format(e)
        } catch (var4: ParseException) {
            var4.printStackTrace()
        }

        return result
    }

    /**
     * 获取年月日
     * @param date
     * @return
     */
    fun parseYyyyMmDd(date: Date): String {
        return YYYYMMDD_FORMAT.format(date)
    }

    /**
     * 时分秒
     * @param dateTime
     * @return
     */
    fun parseHhMmSs(dateTime: String): String {
        try {
            val e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime)
            return HHMMSS_FORMAT.format(e)
        } catch (var2: ParseException) {
            var2.printStackTrace()
            return ""
        }

    }

    /**
     * 时分秒
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    fun parseHhMmSs(dateTime: String, simpleDateFormat: SimpleDateFormat): String {
        try {
            val e = simpleDateFormat.parse(dateTime)
            return HHMMSS_FORMAT.format(e)
        } catch (var3: ParseException) {
            var3.printStackTrace()
            return ""
        }

    }

    /**
     * 时分秒
     * @param date
     * @return
     */
    fun parseHhMmSs(date: Date): String {
        return HHMMSS_FORMAT.format(date)
    }



    /**
     * 将年月日时分秒转成Long类型
     * @param dateTime
     * @return
     */
    fun dateTimeToTimeStamp(dateTime: String): Long? {
        try {
            val e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime)
            return java.lang.Long.valueOf(e.getTime() / 1000L)
        } catch (var2: ParseException) {
            var2.printStackTrace()
            return java.lang.Long.valueOf(0L)
        }

    }

    /**
     * 将Long类型转成年月日时分秒
     * @param timeStamp
     * @return
     */
    fun timeStampToDateTime(timeStamp: Long?): String {
        return YYYYMMDDHHMMSS_FORMAT.format(Date(timeStamp!!.toLong() * 1000L))
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @return
     */
    fun string2Date(time: String): Date? {
        return string2Date(time, YYYYMMDDHHMMSS_FORMAT)
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @param simpleDateFormat
     * @return
     */
    fun string2Date(time: String, simpleDateFormat: SimpleDateFormat): Date? {
        try {
            return simpleDateFormat.parse(time)
        } catch (var3: ParseException) {
            var3.printStackTrace()
            return null
        }

    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @return
     */
    fun date2String(date: Date?): String {
        try {
            return date2String(date!!, YYYYMMDDHHMMSS_FORMAT)

        }catch (e:Exception){
            return "时间格式错误"
        }
    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @param simpleDateFormat
     * @return
     */
    fun date2String(date: Date?, simpleDateFormat: SimpleDateFormat): String {
        return simpleDateFormat.format(date)
    }

    /**
     * 比较日期
     * @param standDate
     * @param desDate
     * @return
     */
    fun dateIsBefore(standDate: String, desDate: String): Boolean {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(desDate).before(YYYYMMDDHHMMSS_FORMAT.parse(standDate))
        } catch (var3: ParseException) {
            var3.printStackTrace()
            return false
        }

    }

    /**
     * 相差多少分钟
     * @param beginDate
     * @param endDate
     * @return
     */
    fun minutesBetweenTwoDate(beginDate: String, endDate: String): Long {
        val millisBegin = dateTimeToTimeStamp(beginDate)!!.toLong()
        val millisEnd = dateTimeToTimeStamp(endDate)!!.toLong()
        return (millisEnd - millisBegin) / 60L
    }



    /**
     * 获取日期中的生肖
     * @param year
     * @return
     */
    fun getChineseZodiac(year: Int): String {
        return CHINESE_ZODIAC[year % 12]
    }


    /**
     * 获取日期中的星座
     * @param month
     * @param day
     * @return
     */
    fun getZodiac(month: Int, day: Int): String {
        return ZODIAC[if (day >= ZODIAC_FLAGS[month - 1]) month - 1 else (month + 10) % 12]
    }

    /**
     * 获取日期
     *
     * @param offset 表示偏移天数
     * @return
     */
    fun getNowDayOffset(offset: Int): String {
        val m_Calendar = Calendar.getInstance()
        var time = m_Calendar.getTimeInMillis() as Long
        time = time + offset * 24 * 3600 * 1000
        val myDate = Date(time)
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(myDate)
    }

    /**
     * 获取日期
     *
     * @param
     * @return
     */
    fun getTime(time: Long): String {
        val myDate = Date(time)
        val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        return df.format(myDate)
    }

    /**
     * 使指定日期向前走一天，变成“明天”的日期
     *
     * @param cal 等处理日期
     */
    fun forward(cal: Calendar) {
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)//0到11
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val days = getDaysOfMonth(year, month + 1)
        if (day == days) {//如果是本月最后一天，还要判断年份是不是要向前滚
            if (month == 11) {//如果是12月份，年份要向前滚
                cal.roll(Calendar.YEAR, true)
                cal.set(Calendar.MONTH, 0)//月份，第一月是0
                cal.set(Calendar.DAY_OF_MONTH, 1)

            } else {//如果不是12月份
                cal.roll(Calendar.MONTH, true)
                cal.set(Calendar.DAY_OF_MONTH, 1)
            }

        } else {
            cal.roll(Calendar.DAY_OF_MONTH, 1)//如果是月内，直接天数加1
        }
    }

    /**
     * 使日期倒一天
     *
     * @param cal
     */
    fun backward(cal: Calendar) {
        //计算上一月有多少天
        val month = cal.get(Calendar.MONTH)//0到11
        val year = cal.get(Calendar.YEAR)
        val days = getDaysOfMonth(year, month)//上个月的天数
        val day = cal.get(Calendar.DAY_OF_MONTH)
        if (day == 1) {//如果是本月第一天，倒回上一月
            if (month == 0) {//如果是本年第一个月，年份倒一天
                cal.roll(Calendar.YEAR, false)
                cal.set(Calendar.MONTH, 11)//去年最后一个月是12月
                cal.set(Calendar.DAY_OF_MONTH, 31)//12月最后一天总是31号
            } else {//月份向前
                cal.roll(Calendar.MONTH, false)
                cal.set(Calendar.DAY_OF_MONTH, days)//上个月最后一天
            }
        } else {
            cal.roll(Calendar.DAY_OF_MONTH, false)//如果是月内，日期倒一天
        }
    }

    /**
     * 判断平年闰年
     *
     * @param year
     * @return true表示闰年，false表示平年
     */
    fun isLeapYear(year: Int): Boolean {
        if (year % 400 == 0) {
            return true
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true
        }
        return false
    }

    /**
     * 计算某月的天数
     *
     * @param year
     * @param month 现实生活中的月份，不是系统存储的月份，从1到12
     * @return
     */

    fun getDaysOfMonth(year: Int, month: Int): Int {
        if (month < 1 || month > 12) {
            return 0
        }
        val isLeapYear = isLeapYear(year)
        var daysOfMonth = 0
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> daysOfMonth = 31
            4, 6, 9, 11 -> daysOfMonth = 30
            2 -> if (isLeapYear) {
                daysOfMonth = 29
            } else {
                daysOfMonth = 28
            }
        }
        return daysOfMonth
    }

    /**
     * 获取当天凌晨的秒数
     *
     * @return
     */
    fun secondsMorning(c: Calendar): Long {
        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0)
        return tempCalendar.getTimeInMillis()
    }

    /**
     * 获取第二天凌晨的秒数
     *
     * @return
     */
    fun secondsNight(c: Calendar): Long {
        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0)
        forward(tempCalendar)
        return tempCalendar.getTimeInMillis()
    }

    /**
     * 判断某两天是不是同一天
     *
     * @param c1
     * @param c2
     * @return
     */
    fun isSameDay(c1: Calendar, c2: Calendar): Boolean {

        if (c1.get(Calendar.YEAR) !== c2.get(Calendar.YEAR))
            return false
        if (c1.get(Calendar.MONTH) !== c2.get(Calendar.MONTH))
            return false
        return if (c1.get(Calendar.DAY_OF_MONTH) !== c2.get(Calendar.DAY_OF_MONTH)) false else true
    }

    /** 日期格式：yyyy-MM-dd HH:mm:ss  */
    val DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

    /** 日期格式：yyyy-MM-dd HH:mm  */
    val DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"

    /** 日期格式：yyyy-MM-dd  */
    val DF_YYYY_MM_DD = "yyyy-MM-dd"

    /** 日期格式：HH:mm:ss  */
    val DF_HH_MM_SS = "HH:mm:ss"

    /** 日期格式：HH:mm  */
    val DF_HH_MM = "HH:mm"

    private val MINUTE = (60 * 1000).toLong()// 1分钟
    private val HOUR = 60 * MINUTE// 1小时
    private val DAY = 24 * HOUR// 1天
    private val MONTH = 31 * DAY// 月
    private val YEAR = 12 * MONTH// 年

    /** Log输出标识  */
    private val TAG = AppDateMgr::class.java.simpleName

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     *
     * @param date
     * @return
     */
    fun formatFriendly(date: Date?): String? {
        if (date == null) {
            return null
        }
        val diff = Date().getTime() - date!!.getTime()
        var r: Long = 0
        if (diff > YEAR) {
            r = diff / YEAR
            return r.toString() + "年前"
        }
        if (diff > MONTH) {
            r = diff / MONTH
            return r.toString() + "个月前"
        }
        if (diff > DAY) {
            r = diff / DAY
            return r.toString() + "天前"
        }
        if (diff > HOUR) {
            r = diff / HOUR
            return r.toString() + "个小时前"
        }
        if (diff > MINUTE) {
            r = diff / MINUTE
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL
     * 日期
     * @return
     */
    fun formatDateTime(dateL: Long): String {
        val sdf = SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS)
        val date = Date(dateL)
        return sdf.format(date)
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL
     * 日期
     * @return
     */
    fun formatDateTime(dateL: Long, formater: String): String {
        val sdf = SimpleDateFormat(formater)
        return sdf.format(Date(dateL))
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param formater
     * 日期
     * @return
     */
    fun formatDateTime(date: Date, formater: String): String {
        val sdf = SimpleDateFormat(formater)
        return sdf.format(date)
    }


    /**
     * 获取系统当前日期
     *
     * @return
     */
    fun gainCurrentDate(): Date {
        return Date()
    }


    /**
     * 对日期进行增加操作
     *
     * @param target
     * 需要进行运算的日期
     * @param hour
     * 小时
     * @return
     */
    fun addDateTime(target: Date?, hour: Double): Date? {
        return if (null == target || hour < 0) {
            target
        } else Date(target!!.getTime() + (hour * 60.0 * 60.0 * 1000.0).toLong())

    }

    /**
     * 对日期进行相减操作
     *
     * @param target
     * 需要进行运算的日期
     * @param hour
     * 小时
     * @return
     */
    fun subDateTime(target: Date?, hour: Double): Date? {
        return if (null == target || hour < 0) {
            target
        } else Date(target!!.getTime() - (hour * 60.0 * 60.0 * 1000.0).toLong())

    }

    private val second = SimpleDateFormat(
            "yy-MM-dd hh:mm:ss")

    private val day = SimpleDateFormat("yyyy-MM-dd")
    private val detailDay = SimpleDateFormat("yyyy年MM月dd日")
    private val fileName = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    private val tempTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private val excelDate = SimpleDateFormat("yyyy/MM/dd")

    /**
     * 格式化excel中的时间
     * @param date
     * @return
     */
    fun formatDateForExcelDate(date: Date): String {
        return excelDate.format(date)
    }

    /**
     * 将日期格式化作为文件名
     * @param date
     * @return
     */
    fun formatDateForFileName(date: Date): String {
        return fileName.format(date)
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param date
     * @return
     */
    fun formatDateSecond(date: Date): String {
        return second.format(date)
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param date
     * @return
     */
    fun tempDateSecond(date: Date): String {
        return tempTime.format(date)
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param str
     * @return
     */
    fun tempDateSecond(str: String): Date {
        try {
            return tempTime.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Date()
    }

    /**
     * 格式化日期(精确到天)
     *
     * @param date
     * @return
     */
    fun formatDateDay(date: Date): String {
        return day.format(date)
    }


    /**
     * 将字符串转换成日期
     *
     * @param date
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun formateDate(date: String): Date {
        return day.parse(date)
    }

    /**
     * 将字符日期转换成Date
     * @param date
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun parseStringToDate(date: String): Date {
        return day.parse(date)
    }


    /**
     * 获得指定Date类型的毫秒数
     * @param date 指定的Date
     * @return 指定Date类型的毫秒数
     */
    fun getTimeMillis(date: Date): Long {
        return date.getTime()
    }

    /**
     * 获得当前时间的毫秒数
     * @return 当前时间的毫秒数
     */
    fun getCurrentDayTimeMillis(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 将格式化过的时间串转换成毫秒
     * @param day 将格式化过的时间
     * @param format 格式化字符串
     * @return 毫秒
     */
    fun convertMillisecond(day: String?, format: String?): Long {
        if (day == null || format == null)
            return 0
        val formatter = SimpleDateFormat(format)
        try {
            val dt = formatter.parse(day)
            return dt.getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return 0
    }

    /**
     * 得到两个日期的天数
     * @param sdate1 日期一
     * @param sdate2 日期二
     * @return 天数
     */
    fun getDateInterval(sdate1: String, sdate2: String): Int {
        var date1: Date? = null
        var date2: Date? = null
        var betweenDays: Long = 0

        try {
            date1 = SimpleDateFormat("yyyy-MM-dd").parse(sdate1)
            date2 = SimpleDateFormat("yyyy-MM-dd").parse(sdate2)

            val beginTime = date1!!.getTime()
            val endTime = date2!!.getTime()
            betweenDays = ((endTime - beginTime) / (1000 * 60 * 60 * 24)).toLong()

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return betweenDays.toInt()
    }

    /**
     * 时间比较
     * @param format 格式化字符串
     * @param time1 时间1
     * @param time2 时间2
     * @return time1比time2早返回-1,time1与time2相同返回0,time1比time2晚返回1
     */
    fun compareTime(format: String?, time1: String?, time2: String?): Int {
        if (format == null || time1 == null || time2 == null)
            return 0
        val formatter = SimpleDateFormat(format)
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()

        try {
            c1.setTime(formatter.parse(time1))
            c2.setTime(formatter.parse(time2))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return c1.compareTo(c2)
    }


    fun getunixforDate():Long{
        val instance = Calendar.getInstance()
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        val today = cal.timeInMillis
        instance.set(2018,12,22)
        val all = instance.timeInMillis
        val swap = all -today
        return swap/(1000*60*60*24)
    }

    fun formatTime(millisecond:Long):String{
        val hour : Long = (millisecond / 1000 / 60 / 60)
        var milliseconds = millisecond - hour*60*60*1000

        val minute: Long = (milliseconds /1000/ 60)//分钟
        val second: Long = (milliseconds /1000% 60)//秒数
        return if (hour < 10){
            if (minute < 10) {
                if (second < 10) {
                    "0$hour:0$minute:0$second"
                } else {
                    "0$hour:0$minute:$second"
                }
            } else {
                if (second < 10) {
                    "0$hour:"+minute.toString() + ":" + "0" + second
                } else {
                    "0$hour:"+minute.toString() + ":" + second
                }
            }
        }else{
            if (minute < 10) {
                if (second < 10) {
                    hour.toString()+":"+"0$minute:0$second"
                } else {
                    hour.toString()+":"+"0$minute:$second"
                }
            } else {
                if (second < 10) {
                    hour.toString()+":"+minute.toString() + ":" + "0" + second
                } else {
                    hour.toString()+":"+minute.toString() + ":" + second
                }
            }
        }


    }


}