package com.lotta.itunesapi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.ActivityMainBinding
import com.lotta.itunesapi.ui.dialog.LoadingDialog
import com.lotta.itunesapi.ui.home.HomeViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initObserve()
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
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initObserve() {
        mainViewState.observe(this@MainActivity) { state ->
            if (state.isLoading) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }
    }

    companion object {
        val mainViewState = MutableLiveData(HomeViewState())
    }
}