package com.batdemir.template.features.splash

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.batdemir.core.extensions.move
import com.batdemir.template.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        move(MainActivity::class.java)
    }
}