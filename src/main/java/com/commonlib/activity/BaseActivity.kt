package com.commonlib.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.aialias.bidding.fragment.BaseFragment
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable


/**
 *create by echo
 */
abstract class BaseActivity: AppCompatActivity() {
    /***是否显示标题栏 */
    private var isshowtitle = true
    /***是否显示标题栏 */
    private var isshowstate = true

    protected val TAG = this.javaClass.simpleName

    protected var appManager = AppManager.getAppManager()

    private val contextString: String? = null // 类名

    protected var mContext:Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if(!isshowtitle){
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        }

        if(isshowstate){
            window.setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN)
        }
        appManager.addActivity(this)
        mContext = this
        //设置布局
        setContentView(intiLayout())
        initToolBar()
        initView()

        //设置数据
        initData()
        //初始化控件
        initListener()

    }

    override fun onDestroy() {
        super.onDestroy()
        appManager.finishActivity(this)
    }

    fun appExit(){
        appManager.finishAllActivity()
    }


    /**
     * 设置布局
     *
     * @return
     */
    abstract fun intiLayout(): Int

    abstract fun initView()
    abstract fun initToolBar()

    /**
     * 初始化布局
     */
    abstract fun initListener()

    /**
     * 设置数据
     */
    abstract fun initData()


    /**
     * 是否设置标题栏
     *
     * @return
     */
    fun setTitle(ishow: Boolean) {
        isshowtitle = ishow
    }

    /**
     * 设置是否显示状态栏
     * @param ishow
     */
    fun setState(ishow: Boolean) {
        isshowstate = ishow
    }

    fun toast(msg: String){
        if (msg != null){
            Toast.makeText(baseContext,msg,Toast.LENGTH_SHORT).show()
        }
    }

    fun arouterAndFinish(uri: String){
        ARouter.getInstance().build(uri).navigation()
        finish()

    }
    fun arouterto(uri: String){
        ARouter.getInstance().build(uri).navigation()
    }

    fun arouterByString(uri:String,key:String,value:String){
        ARouter.getInstance().build(uri)
                .withString(key,value)
                .navigation()
    }

    fun getIntentValue(key:String?):String{
        return intent.extras.getString(key)
    }

    fun arouterFragment(ids:Int,fragment: BaseFragment){
        var frg = supportFragmentManager.beginTransaction()
        frg.replace(ids,fragment)
        frg.commit()
    }



    fun arouterByResult(uri: String, code: Int) {
        ARouter.getInstance().build(uri).navigation(this, code)
    }
    fun arouterStringByResult(uri: String, key: String, value: String, code: Int) {
        ARouter.getInstance().build(uri).withString(key, value).navigation(this, code)
    }


    fun getIntentSerializable(key: String?): Serializable {
        return try {
            intent.extras.getSerializable(key)

        }catch (e:Exception){
            ""
        }
    }


    fun arouterBySerializable(uri:String,key:String,value: Serializable){
        ARouter.getInstance().build(uri)
                .withSerializable(key,value)
                .navigation()
    }




}