package com.commonlib

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 *create by echo
 */
class CommonManager private constructor(){
    var mContext : Context? = null
    var application :Application? = null

    //懒汉模式私有化构造
    companion object {
        val instance: CommonManager by lazy { CommonManager() }
    }

    fun init(context: Context, application: Application){
        mContext = context
        this.application = application
        initARouter()
        initLogger()
    }
    /**
     * 初始化路由
     */
    fun initARouter(){
        if(BuildConfig.DEBUG){ //如果在debug模式下
            // 打印日志,默认关闭
            ARouter.openLog()
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }

        // 尽可能早，推荐在Application中初始化
        ARouter.init(application)
    }

    /**
     * 初始化日志
     */
    fun  initLogger(){
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                //                .methodCount(3)         // （可选）要显示的方法行数。 默认2
                //                .methodOffset(2)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("haitun")//（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Logger.addLogAdapter(DiskLogAdapter())//保存到文件

    }

}