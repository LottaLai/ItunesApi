package com.lotta.itunesapi.ui.video.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lotta.itunesapi.databinding.FragmentVideoBinding
import com.lotta.itunesapi.ui.video.adapter.VideoRecyclerViewAdapter
import com.lotta.itunesapi.ui.video.adapter.VideoRecyclerViewItem
import com.lotta.itunesapi.ui.video.model.Music
import com.lotta.itunesapi.ui.video.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : Fragment(), VideoRecyclerViewAdapter.OnItemClickListener {
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    private val videoViewModel: VideoViewModel by viewModels()
    private val videoRecyclerViewAdapter = VideoRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentVideoBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserve()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = videoRecyclerViewAdapter
        }

        videoRecyclerViewAdapter.itemClickListener = this
    }

    private fun initObserve() {
        videoViewModel.videoListItemsLiveData.observe(viewLifecycleOwner) {
            videoRecyclerViewAdapter.submitList(it)
        }
    }

    override fun onItemClick(view: View, item: VideoRecyclerViewItem, position: Int) {
        when (item) {
            is VideoRecyclerViewItem.MusicItem -> {
                Log.d("Item", "Music")
            }

            is VideoRecyclerViewItem.PromotionItem -> {
                Log.d("Item", "Promotion")
            }

            is VideoRecyclerViewItem.VideoItem -> {
                Log.d("Item", "Promotion")
            }
        }
    }
}