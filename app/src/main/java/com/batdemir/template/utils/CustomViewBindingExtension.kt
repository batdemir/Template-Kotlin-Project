package com.batdemir.template.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.batdemir.template.app.GlideApp
import com.google.android.material.imageview.ShapeableImageView

class CustomViewBindingExtension {
    companion object {
        @JvmStatic
        @BindingAdapter("setImage")
        fun ShapeableImageView.bindImage(res: String?) {
            if (res.isNullOrEmpty())
                return
            GlideApp
                .with(this.context)
                .load(res)
                .into(this)
        }

        @JvmStatic
        @BindingAdapter("setAdapterRecyclerAdapter")
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
            if (adapter == null)
                return
            this.setHasFixedSize(true)
            this.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("setAdapterViewPager2Adapter")
        fun ViewPager2.bindViewPager2Adapter(adapter: RecyclerView.Adapter<*>?) {
            if (adapter == null)
                return
            this.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("setVisibilityForString")
        fun View.bindVisibilityForString(value: String?) {
            if (value.isNullOrEmpty())
                this.dismiss()
            else
                this.show()
        }
    }
}