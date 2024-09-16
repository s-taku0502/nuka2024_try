package com.example.nuka2024_try.ui.qr_scanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.nuka2024_try.MainActivity
import com.example.nuka2024_try.R
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView
import com.example.nuka2024_try.ui.stamps.StampListActivity // スタンプ一覧画面のアクティビティ

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

    /* ホーム画面（スマートフォンのホーム画面）への移動 #1
    fun goHome(view: View) {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    } */

/*    fun saveStamp(stampName: String) {
        val sharedPreferences = getSharedPreferences("stamps", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("stamp1", stampName)  // スタンプ名を保存
        editor.apply()
    }*/


    // 赤い線を消す処理
    private fun disableLaser(decoratedBarcodeView: DecoratedBarcodeView) {
        val scannerAlphaField = ViewfinderView::class.java.getDeclaredField("SCANNER_ALPHA")
        scannerAlphaField.isAccessible = true
        scannerAlphaField.set(decoratedBarcodeView.viewFinder, intArrayOf(0))
    }

/*
    // QRCodeCaptureActivity.kt
    fun ToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java) // MainActivity を適切な Activity に置き換えてください
        startActivity(intent)
    }
*/



    class QRCodeCaptureActivity : AppCompatActivity() {
        // ... 他のメソッド ...

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled",Toast.LENGTH_LONG).show()
                } else {
                    val scannedString = result.contents
                    // 特定の文字列ならスタンプ一覧画面にスタンプを追加
                    if (scannedString == "stamp1") {
                        val intent = Intent(this, StampListActivity::class.java)
                        intent.putExtra("stampId", 1) // スタンプIDを渡す
                        startActivity(intent)
                    } else if (scannedString == "stamp2") {
                        // 他のスタンプの場合
                    } else {
                        Toast.makeText(this, "Invalid QR code", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

}