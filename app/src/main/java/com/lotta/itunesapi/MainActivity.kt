package com.lotta.itunesapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.configuration.ViewModelFactory
import com.lotta.itunesapi.databinding.ActivityMainBinding
import com.lotta.itunesapi.ui.home.HomeViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mActivity = this

        initNavController()
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity = null
    }

    private fun initNavController() {
        binding.navView.apply {
            setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_SELECTED
        }
    }

    companion object {
        var mActivity: AppCompatActivity? = null
            private set

    }
}