package com.example.nuka2024_try.ui.qr_scanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nuka2024_try.R
import com.example.nuka2024_try.ui.stamps.StampsFragment
import com.google.zxing.integration.android.IntentIntegrator

class QR_Scanner_Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        val qrButton = findViewById<Button>(R.id.qr_button)

        // ボタンを押した際にQRスキャン画面に遷移
        qrButton.setOnClickListener {
            IntentIntegrator(this).apply {
                captureActivity = QRCodeCaptureActivity::class.java // QRスキャンアクティビティを設定
            }.initiateScan()
        }
    }

    // QRスキャン結果の処理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val scannedUrl = result.contents
            if (scannedUrl == "https://sites.google.com/view/daimon-dx") {
                addStampToStampsPage(scannedUrl)  // StampsFragment にスタンプを追加
            } else {
                Toast.makeText(this, "無効なQRコードです", Toast.LENGTH_LONG).show()
            }
        }
    }

    // StampsFragmentにスタンプを追加するメソッド
    private fun addStampToStampsPage(stampName: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.stampContainer) as? StampsFragment
        fragment?.addStamp(stampName)
    }
}
