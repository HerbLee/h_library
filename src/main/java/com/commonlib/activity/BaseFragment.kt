package com.aialias.bidding.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.commonlib.`interface`.FragmentBackListener
import java.io.Serializable

/**
 *
 * @author HERB
 * @date Created on 2018/2/13 0:56
 * @function: 基类
 */
abstract class BaseFragment : Fragment() , FragmentBackListener {
    var mView: View? = null
    protected val TAG = this.javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = initView()
        if (mView != null) {
            return mView
        }
        var tv = TextView(activity)
        tv.text = "没有初始化对象"
        mView = tv
        return tv
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        listener()
    }



    /**
     * 写监听事件
     */
    abstract fun listener()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化view
     */
    abstract fun initView(): View?




    fun toastNet(str: String) {
        activity!!.runOnUiThread {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
    }

    fun toast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
    fun arouterAndFinish(uri: String){
        ARouter.getInstance().build(uri).navigation()
        activity!!.finish()

    }
    fun arouterto(uri: String){
        ARouter.getInstance().build(uri).navigation()
    }

    fun arouterByString(uri:String,key:String,value:String){
        ARouter.getInstance().build(uri)
                .withString(key,value)
                .navigation()
    }

    fun arouterBySerializable(uri:String,key:String,value: Serializable){
        ARouter.getInstance().build(uri)
                .withSerializable(key,value)
                .navigation()
    }
    fun getIntentValue(key: String?): String {

        return try {
            arguments!![key] as String
        }catch (e:Exception){
            ""
        }
    }
    fun arouterStringByResult(uri: String, key: String, value: String, code: Int) {
        ARouter.getInstance().build(uri).withString(key, value).navigation(activity, code)
    }

}