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

            navController.addOnDestinationChangedListener { _, destination, argu ->
                supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(false)
                    when (destination.id) {
//                        R.id.homeFragment -> {
//                            setDisplayHomeAsUpEnabled(true)
//                        }
//                        else -> {
//                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initObserve() {
        mainViewState.observe(this) { state ->
            when(state){
                is MainViewState.Loading -> loadingDialog.show()
                is MainViewState.Success<*> -> {
                    loadingDialog.dismiss()
                }
                is MainViewState.Failed<*> -> {
                    loadingDialog.dismiss()
                }
                else -> { throw IllegalArgumentException("Invalid view state type")}
            }
        }
    }

    companion object {
        val mainViewState : MutableLiveData<MainViewState> = MutableLiveData()
    }
}