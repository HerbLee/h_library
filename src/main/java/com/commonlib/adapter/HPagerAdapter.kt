package com.commonlib.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.ViewGroup
import com.aialias.bidding.fragment.BaseFragment
import com.qmuiteam.qmui.widget.QMUIPagerAdapter

/**
 *create by echo
 */
class HPagerAdapter(fra:FragmentManager,data:List<BaseFragment>, title:Array<String>):FragmentPagerAdapter(fra) {

    val mData = data
    val titles = title



    override fun getItem(position: Int): Fragment {
        return mData[position]
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}