package com.batdemir.template.ui.view

import androidx.appcompat.app.AppCompatActivity
import com.batdemir.template.app.MyApplication
import com.batdemir.template.utils.move
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onStart() {
        (application as MyApplication).applicationComponent.inject(this)
        super.onStart()
        move(MainActivity::class.java)
    }
}