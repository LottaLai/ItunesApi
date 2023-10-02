package com.lotta.itunesapi.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.configuration.DaggerViewModelFactory
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.FragmentHomeBinding
import com.lotta.itunesapi.adapter.FilterAdapter
import com.lotta.itunesapi.room.Track
import com.lotta.itunesapi.adapter.TrackAdapter
import java.util.*
import javax.inject.Inject

open class HomeFragment : Fragment(),
    TrackAdapter.OnItemClickListener,
    FilterAdapter.OnButtonClickListener {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var trackAdapter: TrackAdapter
    private var _mediaFilterAdapter: FilterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setHasOptionsMenu(true)
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu_layout, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val regexPattern = Regex("[a-zA-Z ]+")
                if (query != null && regexPattern.matches(query)) {
                    binding.searchRecyclerView.scrollToPosition(0)
                    homeViewModel.termQuery.value = query.replace(" ", "+")
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun initAdapter() {
        trackAdapter = TrackAdapter(this)
        binding.searchRecyclerView.apply {
            adapter = trackAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        _mediaFilterAdapter = FilterAdapter(this)
        binding.mediaFilterRecyclerView.apply {
            adapter = _mediaFilterAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initViewModel() {
        ITunesApp.application.appComponent.inject(this)
        homeViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            )[HomeViewModel::class.java].apply {
                val enLanguage = getEnglishMediaFilterArray()
                val curLanguage = resources.getStringArray(R.array.media_filter_option)
                filterList(enLanguage, curLanguage)
            }
    }

    private fun getEnglishMediaFilterArray(): Array<String> {
        val configuration = Configuration(requireContext().resources.configuration)
        configuration.setLocale(Locale("en"))
        return requireContext().createConfigurationContext(configuration).resources.getStringArray(R.array.media_filter_option)
    }


    private fun initObserve() {
        homeViewModel.apply {
            mediaFilterList.observe(viewLifecycleOwner) {
                _mediaFilterAdapter?.submitList(it)
            }
            tracks.observe(viewLifecycleOwner) {
                trackAdapter.submitData(lifecycle, it)
            }
            filter.observe(viewLifecycleOwner) {
                trackAdapter.submitData(lifecycle, it)
            }
        }

    }

    override fun onItemClick(track: Track) {
        val action = HomeFragmentDirections.actionNavigationHomeToMediaDetailsFragment(track)
        findNavController().navigate(action)
    }

    override fun onButtonClick(item: String) {
        homeViewModel.filterTracks(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}