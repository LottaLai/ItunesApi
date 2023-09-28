package com.lotta.itunesapi.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.SearchView
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
import com.lotta.itunesapi.model.MediaFilterAdapter
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var _homeViewModel: HomeViewModel? = null
    private var _songAdapter: MediaAdapter? = null
    private var _filterAdapter: MediaFilterAdapter? = null

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

    private fun initAdapter(){
        _songAdapter = MediaAdapter()
        _filterAdapter = MediaFilterAdapter()
        binding.searchRecyclerView.apply {
            adapter = _songAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.filterRecyclerView.apply {
            adapter = _filterAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false)
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
            songList.observe(requireActivity()) {
                if (!it.isNullOrEmpty()) {
                    _songAdapter?.submitList(it)
                }
            }
            filterList.observe(requireActivity()){
                if(!it.isNullOrEmpty()){
                    _filterAdapter?.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}