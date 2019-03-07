package com.aialias.hlibraries.utils

import java.util.regex.Pattern

/**
 * @Prject: CommonUtilLibrary
 * @Package: com.aialias.hlibraries.utils
 * @author HERB
 * @date Created on 2018/2/12 20:28
 * @function: 验证App内部的数据
 *@Copyright: Aialias
 * @version: 1.0.0
 */

object AppValidationMgr {

    //验证是不是邮箱
    private val email_pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    //验证手机号
    private val phone_pattern = Pattern.compile("^(13|15|18|17)\\d{9}$")

    /**
     * 验证邮箱
     * @param email 验证邮箱
     * @return boolean
     */
    fun isEmail(email: String): Boolean {
        return email_pattern.matcher(email).matches()
    }

    /**
     * 验证手机号
     * @param phone 手机号码
     * @return boolean
     */
    fun isPhone(phone: String): Boolean {
        return phone_pattern.matcher(phone).matches()
    }

}