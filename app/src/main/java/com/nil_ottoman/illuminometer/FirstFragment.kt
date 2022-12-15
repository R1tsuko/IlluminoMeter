package com.nil_ottoman.illuminometer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nil_ottoman.illuminometer.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val optionsViewModel: OptionsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayListOf(
                "<0,15мм",
                "0,15<0,30мм",
                "0,30<0,50мм",
                "0,50<1,00мм",
                "1,00<5,00мм",
                ">5,00мм"
            )
        )
        binding?.objectSizeFilterSpinner?.setText("<0,15мм")
        binding?.objectSizeFilterSpinner?.setAdapter(adapter)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            optionsViewModel.options.value =
                arrayOf(
                    binding.objectSizeFilterSpinner.text.toString(),
                    resources.getResourceEntryName(binding.background.checkedRadioButtonId),
                    resources.getResourceEntryName(binding.contrast.checkedRadioButtonId),
                    resources.getResourceEntryName(binding.system.checkedRadioButtonId)
                )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}