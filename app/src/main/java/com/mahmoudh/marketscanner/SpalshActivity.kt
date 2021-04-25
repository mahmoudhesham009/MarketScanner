package com.mahmoudh.marketscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpalshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        var thread = Thread(Runnable {
            Thread.sleep(3000)
            startActivity(Intent(this,CodeScannerActivity::class.java))
            finish()
        }).start()
    }
}