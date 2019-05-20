package com.setiawanpaiman.spvnts.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.setiawanpaiman.spvnts.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val photoUrl = intent.getStringExtra(EXTRA_PHOTO_URL)
        Picasso.with(this)
            .load(photoUrl)
            .into(imageView)
    }

    companion object {
        private const val EXTRA_PHOTO_URL = "EXTRA_PHOTO_URL"

        fun newIntent(context: Context, photoUrl: String) = Intent(context, PhotoDetailActivity::class.java)
            .putExtra(EXTRA_PHOTO_URL, photoUrl)
    }
}
