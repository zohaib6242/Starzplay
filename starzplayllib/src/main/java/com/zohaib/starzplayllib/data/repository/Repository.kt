package com.zohaib.starzplayllib.data.repository

import android.util.Log
import com.zohaib.starzplayllib.data.api.ApiService
import com.zohaib.starzplayllib.data.model.CarouselItem
import com.zohaib.starzplayllib.data.model.MediaItem
import com.zohaib.starzplayllib.utils.AppConstant
import retrofit2.HttpException
import java.io.IOException

class Repository(private val apiService: ApiService) {

    suspend fun search(query: String): List<CarouselItem> {
        return try {
            // Attempt to make the API call
            val apiResponse = apiService.searchMulti(AppConstant.API_KEY, query)

            // Group the results by media type
            val itemsByMediaGroup = groupListByMediaType(apiResponse.results)

            // Convert the grouped results into CarouselItems
            convertToCarouselItems(itemsByMediaGroup)

        } catch (e: IOException) {
            // Handle network-related errors
            // Log the error or show a user-friendly message
            Log.e("Search", "Network error occurred: ${e.message}")
            emptyList() // Return an empty list or handle as appropriate

        } catch (e: HttpException) {
            // Handle HTTP-related errors
            // Log the error or show a user-friendly message
            Log.e("Search", "HTTP error occurred: ${e.message}")
            emptyList() // Return an empty list or handle as appropriate

        } catch (e: Exception) {
            // Handle any other unexpected errors
            Log.e("Search", "Unexpected error occurred: ${e.message}")
            emptyList() // Return an empty list or handle as appropriate
        }
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
