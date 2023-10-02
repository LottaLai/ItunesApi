package com.lotta.itunesapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.lotta.itunesapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

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
            navController = findNavController(R.id.nav_host_fragment_activity_main)
            appBarConfiguration = AppBarConfiguration(navController.graph)

            setupActionBarWithNavController(navController, appBarConfiguration)
            setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                supportActionBar?.apply {
                    when (destination.id) {
                        R.id.favoritesFragment -> {
                            setDisplayHomeAsUpEnabled(false)
//                            setDisplayShowTitleEnabled(true)
                        }
                        else -> {
//                            setDisplayShowTitleEnabled(true)
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        var mActivity: AppCompatActivity? = null
            private set

    }
}