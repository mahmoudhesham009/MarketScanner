package com.mahmoudh.marketscanner

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode

class CodeScannerActivity : AppCompatActivity() {
    lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_scanner)
        setPermissions()
    }

    private fun openCamera(){
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.scanMode = ScanMode.CONTINUOUS
        codeScanner.decodeCallback = DecodeCallback {
            val intent = Intent(this, ProductDetailsActivity::class.java).apply {
                putExtra("result", it.toString())
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::codeScanner.isInitialized)
            codeScanner.startPreview()
    }

    override fun onPause() {
        if (this::codeScanner.isInitialized)
            codeScanner.releaseResources()
        super.onPause()
    }

    private fun setPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED)
            askForPermission()
        else
            openCamera()
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 501)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            501 -> {
                if(grantResults.size==0||grantResults.get(0)!= PackageManager.PERMISSION_GRANTED )
                    finish()
                else
                    openCamera()
            }
        }

    }
}