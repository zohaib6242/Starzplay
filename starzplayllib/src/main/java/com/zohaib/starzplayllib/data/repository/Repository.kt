package com.zohaib.starzplayllib.data.repository

import com.zohaib.starzplayllib.data.api.ApiService
import com.zohaib.starzplayllib.data.model.CarouselItem
import com.zohaib.starzplayllib.data.model.MediaItem
import com.zohaib.starzplayllib.utils.AppConstant

class Repository(private val apiService: ApiService) {

    suspend fun search(query: String): List<CarouselItem> {
        val apiResponse = apiService.searchMulti(AppConstant.API_KEY, query)
        val itemsByMediaGroup = groupListByMediaType(apiResponse.results)
        return convertToCarouselItems(itemsByMediaGroup)
    }

    private fun groupListByMediaType(resultList: List<MediaItem>): Map<String, List<MediaItem>> {
        return resultList.groupBy { it.media_type }
    }

    private fun convertToCarouselItems(map: Map<String, List<MediaItem>>): List<CarouselItem> {
        return map.map { (mediaType, mediaItems) ->
            CarouselItem(mediaType, mediaItems)
        }
    }
}
