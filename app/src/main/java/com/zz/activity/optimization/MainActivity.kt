package com.zz.activity.optimization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zz.lightning.Lighting
import com.zz.lightning.LightningLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Lighting.preLoading(this,R.layout.test_ac)

        button.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
        }
    }
}
