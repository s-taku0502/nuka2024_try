package com.example.nuka2024_try.ui.stores

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nuka2024_try.R
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask

// グローバル変数
private var currentImageIndex = 0
private val images = arrayOf(
    R.drawable.icon,
    R.drawable.app_icon,
    R.drawable.nav_icon_stamp
)

private var timer: Timer? = null
private val handler = Handler(Looper.getMainLooper())

class StampFragment : Fragment(R.layout.fragment_stores) {

    // ImageSwitcherをクラス内で保持するためにlateinitを使用
    private lateinit var imageSwitcher: ImageSwitcher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view.findViewByIdでImageSwitcherを取得
        imageSwitcher = view.findViewById(R.id.icon_01)

        // ImageSwitcherにImageViewを設定
        imageSwitcher.setFactory {
            ImageView(requireContext()).apply {
                layoutParams = FrameLayout.LayoutParams(150, 150)
                scaleType = ImageView.ScaleType.FIT_CENTER
            }
        }

        // 初期画像を設定
        imageSwitcher.setImageResource(images[currentImageIndex])

        // マウスオーバー時の処理
        imageSwitcher.setOnHoverListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_HOVER_ENTER -> {
                    startImageSwitching() // 画像の切り替えを開始
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    stopImageSwitching() // 画像の切り替えを停止
                }
            }
            true
        }
    }

    // 画像を3秒ごとに切り替える関数
    private fun startImageSwitching() {
        // タイマーが既に動いている場合は何もしない
        if (timer != null) return

        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post {
                    // 画像を切り替える
                    currentImageIndex = (currentImageIndex + 1) % images.size
                    imageSwitcher.setImageResource(images[currentImageIndex])
                }
            }
        }, 0, 3000) // 3秒ごとに実行
    }

    // 画像の切り替えを停止する関数
    private fun stopImageSwitching() {
        timer?.cancel()
        timer = null
    }
}
