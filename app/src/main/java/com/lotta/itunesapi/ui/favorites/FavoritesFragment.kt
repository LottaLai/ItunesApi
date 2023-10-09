package com.lotta.itunesapi.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.FragmentFavoritesBinding
import com.lotta.itunesapi.adapter.FavoritesAdapter
import com.lotta.itunesapi.room.Track
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(),
    FavoritesAdapter.OnItemClickListener {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserve()
    }

    private fun initAdapter() {
        favoritesAdapter = FavoritesAdapter(this)
        binding.bookmarkRecyclerView.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initViewModel() {
//        ITunesApp.application.appComponent.inject(this)
//        favoritesViewModel =
//            ViewModelProvider(requireActivity(), viewModelFactory)[FavoritesViewModel::class.java]
//                .apply {
//                    getAllFavorites()
//                }
        favoritesViewModel.getAllFavorites()
    }

    private fun initObserve() {
        favoritesViewModel.apply {
            favorites.observe(viewLifecycleOwner) {
                favoritesAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(track: Track) {
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToMediaDetailsFragment(track)
        findNavController().navigate(action)
    }
}