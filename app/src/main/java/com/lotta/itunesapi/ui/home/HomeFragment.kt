package com.lotta.itunesapi.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lotta.itunesapi.configuration.DaggerViewModelFactory
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var _homeViewModel: HomeViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initViewModel() {
        ITunesApp.application.appComponent.inject(this)
        _homeViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
                .apply {
                    fetchData(
                        term = "jack+johnson"
                    )
                }
    }

    private fun initObserve() {
        _homeViewModel?.apply {
            ebookList.observe(requireActivity()) {
                if(!it.isNullOrEmpty()){

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}