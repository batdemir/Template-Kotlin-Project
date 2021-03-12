package com.batdemir.template.ui.view.presentation

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.widget.ImageView
import com.batdemir.template.R


class SecondaryDisplay constructor(
    outerContext: Context,
    display: Display?,
    private val sourceId: Int
) :
    Presentation(outerContext, display) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_display)
        val imageView = findViewById<ImageView>(R.id.image_view)
        imageView.setImageResource(sourceId)
    }
}