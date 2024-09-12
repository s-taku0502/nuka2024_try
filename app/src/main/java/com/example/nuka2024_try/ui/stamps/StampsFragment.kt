package com.example.nuka2024_try.ui.stamps

import androidx.fragment.app.Fragment
import com.example.nuka2024_try.databinding.FragmentSlideshowBinding
import com.example.nuka2024_try.databinding.FragmentStampsBinding

class StampsFragment : Fragment() {

    private var _binding: FragmentStampsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    } */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}