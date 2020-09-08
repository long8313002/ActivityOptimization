package com.zz.lightning

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.View.MeasureSpec
import android.view.WindowManager

internal class CreateViewMeasureWrap(var base: Lighting.ICreateView) : Lighting.ICreateView {

    companion object {
        private var screenWidth = 0
        private var screenHeight = 0

        private fun initScreenSize(context: Context) {
            val metric = DisplayMetrics()
            val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            manager.defaultDisplay.getRealMetrics(metric)
            screenWidth = metric.widthPixels
            screenHeight = metric.heightPixels
        }
    }


    override fun createView(context: Context, resId: Int): View {
        if (screenHeight == 0 || screenWidth == 0) {
            initScreenSize(context)
        }
        val view = base.createView(context, resId)
        val widthSpec = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(screenHeight, MeasureSpec.EXACTLY)
        view.measure(widthSpec, heightSpec)
        view.layout(0, 0, screenWidth, screenHeight)
        return view
    }

}