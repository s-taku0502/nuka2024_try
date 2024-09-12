package com.example.nuka2024_try.ui.qr_scanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.nuka2024_try.R
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView

class QRCodeCaptureActivity : CaptureActivity() {
    private lateinit var capture: CaptureManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_qrcode)

        var barcodeScannerView =
            findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)

        // デフォルトで表示される赤い線を消す
        disableLaser(barcodeScannerView!!)

        var capture = CaptureManager(this, barcodeScannerView!!)
        capture!!.initializeFromIntent(intent, savedInstanceState)
        capture!!.decode()
    }

    override fun onResume() {
        super.onResume()
        var barcodeView =
            findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        var barcodeView =
            findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        var barcodeView =
            findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.pause()
    }

    fun saveStamp(stampName: String) {
        val sharedPreferences = getSharedPreferences("stamps", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("stamp1", stampName)  // スタンプ名を保存
        editor.apply()
    }


    // 赤い線を消す処理
    private fun disableLaser(decoratedBarcodeView: DecoratedBarcodeView) {
        val scannerAlphaField = ViewfinderView::class.java.getDeclaredField("SCANNER_ALPHA")
        scannerAlphaField.isAccessible = true
        scannerAlphaField.set(decoratedBarcodeView.viewFinder, intArrayOf(0))
    }

}