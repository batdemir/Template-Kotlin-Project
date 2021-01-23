package com.batdemir.template.ui.view

import androidx.appcompat.app.AppCompatActivity
import com.batdemir.template.utils.move

class SplashActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        move(MainActivity::class.java)
    }
}