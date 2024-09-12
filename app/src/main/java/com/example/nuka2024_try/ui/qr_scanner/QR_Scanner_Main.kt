package com.example.nuka2024_try.ui.qr_scanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nuka2024_try.R
import com.google.zxing.integration.android.IntentIntegrator

class QR_Scanner_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //アクティビティの画面として、 "R.layout.****" の****を指定している。
        setContentView(R.layout.activity_qrscanner)



        var qrButton = findViewById<Button>(R.id.qr_button)


        //ボタンを押した際QR読み取り画面に遷移する
        qrButton.setOnClickListener{
            IntentIntegrator(this).apply{
                captureActivity= QRCodeCaptureActivity::class.java
            }.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null && result.contents != null) {
            val scannedUrl = result.contents

            // 2. 読み取った内容が指定のURLと一致するか確認
            if (scannedUrl == "https://sites.google.com/view/daimon-dx") {
                // 一致した場合、スタンプを反映する処理
                addStampToStampsPage()
            } else {
                Toast.makeText(this, "無効なQRコードです", Toast.LENGTH_LONG).show()
            }
        }
    }

    // スタンプを反映するメソッドをonActivityResultの外に定義
    fun addStampToStampsPage() {
        // スタンプ画像を表示する処理
        val stampImageView = findViewById<ImageView>(R.id.stamp_image_view)
        stampImageView.setImageResource(R.drawable.icon)  // スタンプ画像をセット

        // スタンプが追加されたことを通知する
        Toast.makeText(this, "スタンプが追加されました", Toast.LENGTH_LONG).show()

        // 必要なら、データベースに保存する処理を追加
        // saveStampToDatabase() のようなメソッドを呼び出してスタンプを保存
    }


}
