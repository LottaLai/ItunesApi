package com.lotta.itunesapi.ui.mediaDetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lotta.itunesapi.configuration.DaggerViewModelFactory
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.FragmentMediaDetailsBinding
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.ui.home.HomeViewModel
import javax.inject.Inject

class MediaDetailsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private var _binding: FragmentMediaDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<MediaDetailsFragmentArgs>()
    private var track: Track? = null

    private lateinit var mediaDetailsViewModel: MediaDetailsViewModel

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

        binding.btnBookmark.apply {
            track?.let {
                isEnabled = mediaDetailsViewModel.getBookMarked(it)
            }
        }

        binding.btnBookmark.setOnClickListener {
            track?.let { it1 -> mediaDetailsViewModel.setBookmark(it1) }
        }
    }

    private fun initViewModel() {
        ITunesApp.application.appComponent.inject(this)
        mediaDetailsViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            )[MediaDetailsViewModel::class.java]
    }
}