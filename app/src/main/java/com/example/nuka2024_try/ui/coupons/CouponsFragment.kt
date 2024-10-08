package com.example.nuka2024_try.ui.coupons

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nuka2024_try.databinding.FragmentCouponsBinding
import com.example.nuka2024_try.ui.stores.CouponsViewModel
import com.example.nuka2024_try.ui.stores.StoresViewModel
//import com.example.nuka2024_try.ui.stores.StoresViewModel

class CouponsFragment : Fragment() {

    private var _binding: FragmentCouponsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(CouponsViewModel::class.java)

        _binding = FragmentCouponsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView_1: TextView = binding.textCoupons

        val spannable_1 =
            SpannableString("額地区スタンプらり～\nスタンプらり～を活用して、商店街をまわろう！")

        // "スタンプらり～" のサイズを20spに設定

        spannable_1.setSpan(StyleSpan(Typeface.BOLD), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable_1.setSpan(AbsoluteSizeSpan(20, true), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // "商店街をまわろう！" のサイズを18spに設定

        spannable_1.setSpan(
            AbsoluteSizeSpan(18, true),
            11,
            spannable_1.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView_1.setTextColor(Color.BLACK)
        textView_1.gravity = Gravity.CENTER  // テキストを中央揃えに設定


        textView_1.text = spannable_1


         /* パディングはTextViewで設定
        textView_2.setPadding(16, 16, 16, 16)
        textView_2.text = quoteText */

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}