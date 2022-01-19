package com.batdemir.template.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.batdemir.template.utils.move
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        move(MainActivity::class.java)
    }
}