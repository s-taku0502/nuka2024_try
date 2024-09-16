package com.example.nuka2024_try.ui.questionnaire

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nuka2024_try.R // 正しいRクラスをインポート

class QuestionnaireActivity : AppCompatActivity() { // アクティビティに継承

    private lateinit var genderSpinner: Spinner
    private lateinit var familySpinner: Spinner
    private lateinit var methodSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire) // レイアウトを設定

        genderSpinner = findViewById(R.id.genderSpinner) // スピナーのIDを修正
        familySpinner = findViewById(R.id.familySpinner)
        methodSpinner = findViewById(R.id.methodSpinner)

        setupSpinners(this) // スピナーを設定
    }

    private fun setupSpinners(context: Context) { // コンテキストを受け取る
        val genderOptions = arrayOf("男性", "女性", "その他")
        val familyOptions = arrayOf("一人暮らし", "夫婦", "家族")
        val methodOptions = arrayOf("車", "バス", "電車", "徒歩")

        // 性別スピナーにデータを設定
        val genderAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = genderAdapter

        // 家族層スピナーにデータを設定
        val familyAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, familyOptions)
        familyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        familySpinner.adapter = familyAdapter

        // 来場方法スピナーにデータを設定
        val methodAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, methodOptions)
        methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        methodSpinner.adapter = methodAdapter

        val db = FirebaseFirestore.getInstance()

        val ageField = findViewById<EditText>(R.id.ageField)
        val submitButton = findViewById<Button>(R.id.sendButton)

        submitButton.setOnClickListener {
            val age = ageField.text.toString()
            val gender = genderSpinner.selectedItem.toString()
            val family = familySpinner.selectedItem.toString()
            val method = methodSpinner.selectedItem.toString()

            // 送信するデータを準備
            val data = hashMapOf(
                "age" to age,
                "gender" to gender,
                "family" to family,
                "method" to method
            )

            // Firebase Firestoreにデータを送信
            db.collection("survey_responses")
                .add(data)
                .addOnSuccessListener {
                    // 成功時の処理
                    Toast.makeText(this, "データが送信されました", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // 失敗時の処理
                    Toast.makeText(this, "エラー: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}