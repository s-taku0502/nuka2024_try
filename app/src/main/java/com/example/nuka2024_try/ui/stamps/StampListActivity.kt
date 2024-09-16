package com.example.nuka2024_try.ui.stamps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nuka2024_try.R
import com.example.nuka2024_try.Stamp
import com.example.nuka2024_try.StampDetailActivity // スタンプ詳細画面のアクティビティ

class StampListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var stampAdapter: StampAdapter
    private val stampList = mutableListOf<Stamp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_list)

        recyclerView = findViewById(R.id.stamp_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 5) // 5列のグリッドレイアウト
        stampAdapter = StampAdapter(stampList)
        recyclerView.adapter = stampAdapter

        // QRコード読み取り画面から渡されたスタンプIDを取得
        val stampId = intent.getIntExtra("stampId", -1)
        if (stampId != -1) {
            addStamp(stampId)
        }

        // スタンプがクリックされたときの処理
        stampAdapter.setOnItemClickListener { stamp ->
            val intent = Intent(this, StampDetailActivity::class.java)
            with(intent) {
                putExtra("stamp", stamp) // スタンプオブジェクトを渡す
                startActivity(this)
            }
        }
    }

    private fun addStamp(stampId: Int) {
        // スタンプIDに対応するスタンプデータを作成
        val newStamp = when (stampId) {
            1 -> Stamp(R.drawable.icon, "スタンプ1")
            2 -> Stamp(R.drawable.icon, "スタンプ2")
            // 他のスタンプの場合
            else -> null
        }

        if (newStamp != null) {
            stampList.add(newStamp)
            stampAdapter.notifyItemInserted(stampList.size - 1)
        }
    }
}