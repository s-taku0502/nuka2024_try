package com.example.nuka2024_try.ui.qr_scanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nuka2024_try.R
import com.google.zxing.integration.android.IntentIntegrator

class QR_Scanner_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //アクティビティの画面として、R.layout.****の****を指定している。
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

        //読み取った結果の受け取り
        val result = IntentIntegrator.parseActivityResult(resultCode,data)
        if(result.contents != null){
            //読み取った結果をトーストで表示する
            Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
        }
    }
}
