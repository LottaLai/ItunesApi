package com.lotta.itunesapi.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.configuration.DaggerViewModelFactory
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.FragmentHomeBinding
import com.lotta.itunesapi.model.MediaAdapter
import com.lotta.itunesapi.model.FilterAdapter
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var _homeViewModel: HomeViewModel? = null
    private var _songAdapter: MediaAdapter? = null
    private var _mediaFilterAdapter: FilterAdapter? = null
    private var _countryFilterAdapter: FilterAdapter? = null

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
        initAdapter()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu_layout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        _songAdapter = MediaAdapter()
        _mediaFilterAdapter = FilterAdapter {
            _homeViewModel?.filterMediaList(it)
        }
        _countryFilterAdapter = FilterAdapter {
            _homeViewModel?.filterCountryList(it)
        }
        binding.searchRecyclerView.apply {
            adapter = _songAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.mediaFilterRecyclerView.apply {
            adapter = _mediaFilterAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        binding.countryFilterRecyclerView.apply {
            adapter = _countryFilterAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initViewModel() {
        ITunesApp.application.appComponent.inject(this)
        _homeViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
                .apply {
                    fetchAllDate(
                        term = "joey+wong"
                    )
                }
    }

    private fun initObserve() {
        _homeViewModel?.apply {
//            songList.observe(requireActivity()) {
//                _songAdapter?.submitList(it)
//            }
//            mediaFilterList.observe(requireActivity()) {
//                _mediaFilterAdapter?.submitList(it)
//            }
//            countryFilterList.observe(requireActivity()) {
//                _countryFilterAdapter?.submitList(it)
//            }
            paging.observe(requireActivity()){
                it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}