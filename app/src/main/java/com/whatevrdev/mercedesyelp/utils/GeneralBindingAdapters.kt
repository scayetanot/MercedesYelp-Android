package com.whatevrdev.mercedesyelp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class GeneralBindingAdapters {
//    @BindingAdapter("itemImage", "shouldOverride", requireAll = false)
    fun ImageView.itemImage(imageUrl: String?, shouldOverride: Boolean = false) {
        imageUrl?.let { url ->
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()

            try {
                Glide
                    .with(this.context.applicationContext)
                    .load(url)
                    .apply(options)
                    .into(this)
            } catch (e: Exception) {
                Log.e("","Thumbnail Failed in Glide...using error drawable resource")
            }
        }
    }
}