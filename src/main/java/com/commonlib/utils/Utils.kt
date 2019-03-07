package com.aialias.hlibraries.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import java.util.regex.Pattern


/**
 *
 * @author HERB
 * @date Created on 2018/2/25 0:32
 * @function:
 */
object Utils {

    //获取屏幕宽度
    fun getScreenWidth(context: Context): Int {
        val systemService = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return systemService.defaultDisplay.width
    }
    fun getScreenHeight(context: Context): Int {
        val systemService = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return systemService.defaultDisplay.height
    }

    fun showDialog(context: Context?,ss:Float){
        val activity = context as Activity
        val lp = activity.window.attributes
        lp.alpha = ss
        activity.window.attributes = lp
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    fun isInteger(str: String?): Boolean {
        if (str == null || str.isEmpty()){
            return false
        }
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }
}