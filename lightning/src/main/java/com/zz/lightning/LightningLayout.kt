package com.zz.lightning

import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout

class LightningLayout(context: Context,var resId:Int) : FrameLayout(context){

    companion object{
        private val TAG = "LightningMeasure"
    }

    var loadedTime = 0L
    
    init {
        var rootView = Lighting.findLayout(resId)
        Lighting.removeLayout(resId)
        if(rootView == null){
            rootView = LayoutInflater.from(context).inflate(resId,null)
        }
        addView(rootView)
        Lighting.preLoading(context,resId)
        loadedTime = System.currentTimeMillis()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.w(TAG, "准备时间==>" + (System.currentTimeMillis() - loadedTime) + "ms")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val preTime = System.currentTimeMillis()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.w(TAG, "测量时间==>" + (System.currentTimeMillis() - preTime) + "ms")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val preTime = System.currentTimeMillis()
        super.onLayout(changed, left, top, right, bottom)
        Log.w(TAG, "布局时间==>" + (System.currentTimeMillis() - preTime) + "ms")
    }

    override fun dispatchDraw(canvas: Canvas?) {
        val preTime = System.currentTimeMillis()
        super.dispatchDraw(canvas)
        Log.w(TAG, "绘制时间==>" + (System.currentTimeMillis() - preTime) + "ms")
    }
}