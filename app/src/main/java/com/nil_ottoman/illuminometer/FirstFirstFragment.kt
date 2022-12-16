package com.nil_ottoman.illuminometer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nil_ottoman.illuminometer.databinding.FragmentFirstBinding
import com.nil_ottoman.illuminometer.databinding.FragmentFirstFirstBinding

class FirstFirstFragment : Fragment() {

    private var _binding: FragmentFirstFirstBinding? = null
    private val optionsViewModel: OptionsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFirstFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonFirstFirst.setOnClickListener {
            when(resources.getResourceEntryName(binding.isNeededFirst.checkedRadioButtonId)) {
                "another" -> {
                    findNavController().navigate(R.id.action_FirstFirstFragment_to_FirstFragment)
                    optionsViewModel.isNeededSecondFragment.value = true
                }
                else -> {
                    findNavController().navigate(R.id.action_FirstFirstFragment_to_SecondFragment)
                    optionsViewModel.isNeededSecondFragment.value = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}