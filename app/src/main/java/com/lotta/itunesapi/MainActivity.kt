package com.lotta.itunesapi

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.lotta.itunesapi.configuration.ITunesApp
import com.lotta.itunesapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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