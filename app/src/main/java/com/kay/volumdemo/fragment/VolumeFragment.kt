package com.kay.volumdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kay.volumdemo.R
import com.kay.volumdemo.databinding.FragmentVolumBinding

class VolumeFragment : Fragment() {

    private var _binding: FragmentVolumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVolumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setLinesBtn.setOnClickListener{
            val lineString = binding.setLineInput.editText?.text?.toString()
            val numberOfLines = lineString?.toIntOrNull()
            numberOfLines?.let { binding.volumeView.updateNumberOfLines(it) }
        }

        binding.setVolumeBtn.setOnClickListener{
            val volumString = binding.setVolumeInput.editText?.text?.toString()
            val volume = volumString?.toIntOrNull()
            volume?.let {
                var finalVolume = it
                if (finalVolume < 0) {
                    finalVolume = 0
                } else if (finalVolume > 100) {
                    finalVolume = 100
                }
                binding.volumeView.updateVolumeLevel(finalVolume) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}