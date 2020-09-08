package com.zz.activity.optimization

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver.OnDrawListener
import com.zz.lightning.LightningLayout

class TestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preTime = System.currentTimeMillis()
        setContentView(LightningLayout(this, R.layout.test_ac))
        window.decorView.viewTreeObserver.addOnDrawListener(object : OnDrawListener {
            override fun onDraw() {
                Log.e("LightningMeasure", "加载时间==>" + (System.currentTimeMillis() - preTime) + "ms")
                window.decorView.post { window.decorView.viewTreeObserver.removeOnDrawListener(this) }
            }
        })
    }
}