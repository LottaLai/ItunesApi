package com.lotta.itunesapi.ui.mediaDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerControlView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.FragmentMediaDetailsBinding
import com.lotta.itunesapi.room.Track
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailsFragment : Fragment() {
    private var _binding: FragmentMediaDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<MediaDetailsFragmentArgs>()
    private var track: Track? = null

    private val mediaDetailsViewModel: MediaDetailsViewModel by viewModels()
    private lateinit var exoPlayer: SimpleExoPlayer
    private var mediaItem: MediaItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        track = navArgs.track
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        binding.playerView.apply { player = exoPlayer }

        initView()
        initObserve()
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.playerView.player = null
        mediaItem = null
        exoPlayer.release()
    }

    private fun initViewModel() {
        track?.let { mediaDetailsViewModel.getBookmarked(it) }
    }

    private fun initView() {
        track?.let { it ->
            if (it.kind.equals("song")) {
                Glide.with(binding.root).load(it.artworkUrl100).into(binding.thumbnailImageView)
                binding.thumbnailImageView.visibility = View.VISIBLE
            }
            mediaItem = it.previewUrl?.let { previewUrl -> MediaItem.fromUri(previewUrl) }
            mediaItem?.let { mediaItem -> exoPlayer.setMediaItem(mediaItem) }

            exoPlayer.prepare()
            exoPlayer.play()

            binding.songNameTextView.text = it.trackName
            binding.artistTextView.text = it.artistName
        }

        val controlView =
            binding.playerView.findViewById<PlayerControlView>(com.google.android.exoplayer2.ui.R.id.exo_controller)
        controlView.showTimeoutMs = -1
        controlView.setShowMultiWindowTimeBar(true)
        controlView.show()
    }

    private fun initObserve() {
        mediaDetailsViewModel.apply {
            isBookmarked.observe(viewLifecycleOwner) {
                binding.btnBookmark.apply {
                    if (it) {
                        setIconResource(R.drawable.ic_baseline_bookmark_24)
                    } else {
                        setIconResource(R.drawable.ic_baseline_bookmark_border_24)
                    }
                }
            }
        }
    }

    private fun initListener() {
        binding.btnBookmark.setOnClickListener {
            track?.let { track ->
                mediaDetailsViewModel.setBookmark(track)
            }
        }
    }
}