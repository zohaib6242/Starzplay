package com.zohaib.starzplay.ui.player

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import com.zohaib.starzplay.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private val STREAMING_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializePlayer()
    }

    private fun initializePlayer() {
        // Initialize ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.playerView.player = exoPlayer

        // Prepare the media item
        val mediaUrl = STREAMING_URL
        val mediaItem = MediaItem.fromUri(Uri.parse(mediaUrl))

        // Set the media item to be played
        exoPlayer.setMediaItem(mediaItem)

        // Prepare the player
        exoPlayer.prepare()

        // Start playback
        exoPlayer.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer?.release()
    }
}