package com.example.nuka2024_try.ui.stamps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nuka2024_try.R
import com.google.android.flexbox.FlexboxLayout

class StampsFragment : Fragment() {

    private var stampCount = 0
    private lateinit var stampTextView: TextView
    private lateinit var stampContainer: FlexboxLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // フラグメントのレイアウトを初期化
        val view = inflater.inflate(R.layout.fragment_stamps, container, false)
        stampTextView = view.findViewById(R.id.stampTextView)
        stampContainer = view.findViewById(R.id.stampContainer)
        return view
    }

    // スタンプを動的に追加するメソッド
    fun addStamp(stampName: String) {
        stampCount++

        // スタンプ名に応じてスタンプ画像を選択
        when (stampName) {
            "aiueo" -> addStampIcon(R.drawable.icon) // aiueo に対応する画像
            "qwerty" -> addStampIcon(R.drawable.app_icon) // qwerty に対応する画像
            // 他のスタンプ名に応じた画像も追加可能
        }

        updateStampUI()
        saveStamp(stampName)  // スタンプを保存する
    }

    // UIをスタンプ数に応じて更新
    private fun updateStampUI() {
        stampTextView.text = "Stamps: $stampCount"
    }

    // FlexboxLayoutにスタンプアイコンを動的に追加する
    private fun addStampIcon(resourceId: Int) {
        // 新しいImageViewを作成して、画像リソースを設定
        val imageView = ImageView(context)
        imageView.setImageResource(resourceId)

        // ImageViewのレイアウトパラメータを設定（サイズとマージン）
        val layoutParams = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16, 16, 16, 16) // スタンプ同士の間隔を調整
        imageView.layoutParams = layoutParams

        // FlexboxLayoutにImageViewを追加
        stampContainer.addView(imageView)
    }

    // スタンプをSharedPreferencesに保存する
    private fun saveStamp(stampName: String) {
        val sharedPreferences = activity?.getSharedPreferences("stamps", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("stamp$stampCount", stampName)  // スタンプ名を保存
        editor?.apply()
    }
}
