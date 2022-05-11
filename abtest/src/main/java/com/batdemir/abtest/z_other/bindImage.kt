package com.batdemir.abtest.z_other

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun ImageView.bindImage(res: String?) {
    if (res.isNullOrEmpty())
        return
    Glide
        .with(this.context)
        .load(res)
        .into(this)
}