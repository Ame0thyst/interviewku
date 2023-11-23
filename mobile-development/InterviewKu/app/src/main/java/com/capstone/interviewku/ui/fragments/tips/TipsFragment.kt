package com.capstone.interviewku.ui.fragments.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.interviewku.databinding.FragmentTipsBinding
import com.capstone.interviewku.ui.ViewModelFactory

class TipsFragment : Fragment() {
    private lateinit var viewModel: TipsViewModel

    private var _binding: FragmentTipsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext())
        )[TipsViewModel::class.java]
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}