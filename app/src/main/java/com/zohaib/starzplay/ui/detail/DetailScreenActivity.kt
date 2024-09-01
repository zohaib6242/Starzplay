package com.zohaib.starzplay.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.zohaib.starzplay.R
import com.zohaib.starzplay.databinding.ActivityDetailScreenBinding
import com.zohaib.starzplay.ui.player.PlayerActivity
import com.zohaib.starzplayllib.data.model.MediaItem
import com.zohaib.starzplayllib.utils.AppConstant

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        initData()
    }

    private fun initData() {
        val mediaItem = intent.getSerializableExtra("mediaItem")
        mediaItem?.let {
            val mediaItemNonNull = it as MediaItem
            binding.carouselTitle.text = mediaItemNonNull.name
            binding.carouselDetails.text = mediaItemNonNull.overview
            Glide.with(this)
                .load(AppConstant.BASE_URL_IMAGE + mediaItemNonNull.poster_path)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder_error)
                .into(binding.mediaImageView)
            if (mediaItemNonNull.media_type in setOf("tv", "movie")) {
                binding.btnPlayBack.visibility = View.VISIBLE
            }
            else{
                binding.btnPlayBack.visibility = View.GONE
            }
        }


    }

    private fun setupUi() {
        binding.btnPlayBack.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}