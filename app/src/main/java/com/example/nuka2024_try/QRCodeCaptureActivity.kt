package com.example.nuka2024_try

import android.os.Bundle
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView

class QRCodeCaptureActivity : CaptureActivity() {
    private lateinit var capture: CaptureManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_qrcode )

        var  barcodeScannerView = findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)

        // デフォルトで表示される赤い線を消す
        disableLaser(barcodeScannerView!!)

        var capture = CaptureManager(this, barcodeScannerView!!)
        capture!!.initializeFromIntent(intent, savedInstanceState)
        capture!!.decode()
    }

    override fun onResume() {
        super.onResume()
        var  barcodeView = findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        var barcodeView = findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        var barcodeView = findViewById<com.journeyapps.barcodescanner.CompoundBarcodeView>(R.id.qrcode_reader)
        barcodeView.pause()
    }

    // 赤い線を消す処理
    private fun disableLaser(decoratedBarcodeView: DecoratedBarcodeView) {
        val scannerAlphaField = ViewfinderView::class.java.getDeclaredField("SCANNER_ALPHA")
        scannerAlphaField.isAccessible = true
        scannerAlphaField.set(decoratedBarcodeView.viewFinder, intArrayOf(0))
    }
}
