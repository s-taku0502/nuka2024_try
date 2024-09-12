package com.example.nuka2024_try.ui.qr_scanner

import android.text.SpannableString
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nuka2024_try.databinding.FragmentQrcodeBinding
import com.example.nuka2024_try.databinding.FragmentSlideshowBinding

class ScannersFragment : Fragment() {

    private var _binding: FragmentQrcodeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val QrcodeViewModel =
            ViewModelProvider(this).get(QRCodeCaptureActivity::class.java)

        _binding = FragmentQrcodeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQrcode
        qrcodeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    } */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //  引用文の作成


    // val textView_2: TextView = binding.textQuote

    val quoteText = SpannableString("この画面はQRcodeスキャナーです")


}