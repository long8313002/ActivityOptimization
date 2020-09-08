package com.zz.lightning

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.core.util.set

object Lighting {

    interface ICreateView {
        fun createView(context: Context, resId: Int): View
    }

    private var handler: Handler

    init {
        val ht = HandlerThread("Lighting")
        ht.start()
        handler = Handler(ht.looper)
    }

    private val layoutMap = SparseArray<View>()

    /**
     * 预加载xml布局
     */
    fun preLoading(context: Context, resId: Int) {
        handler.post {
            layoutMap[resId] = CreateViewMeasureWrap(CreateViewImpl()).createView(context.applicationContext, resId)
        }
    }

    fun findLayout(resId: Int): View? {
        return layoutMap[resId]
    }

    fun removeLayout(resId: Int){
        layoutMap.remove(resId)
    }
}

class CreateViewImpl : Lighting.ICreateView {
    override fun createView(context: Context, resId: Int): View {
        return LayoutInflater.from(context).inflate(resId, null)
    }

}