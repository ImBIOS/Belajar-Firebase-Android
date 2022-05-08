package com.example.belajarfirebase2.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.belajarfirebase2.R

class SliderAdapter (private var dataSlider : ArrayList<Int>, var context: Activity?) : PagerAdapter(){

    lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int {
        return dataSlider.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.items_slider, container, false)

        val imgViewSlider : ImageView = view.findViewById(R.id.img_slider)

        imgViewSlider.setImageResource(dataSlider[position])
        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}