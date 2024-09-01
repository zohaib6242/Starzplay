package com.zohaib.starzplayllib.data.model

data class MediaItem(
    val id: Int,
    val media_type: String,
    val name: String?,
    val overview: String?,
    val poster_path: String?
) : java.io.Serializable
