package com.leovieira.diffutils.repository

import com.leovieira.diffutils.model.Image
import com.leovieira.diffutils.service.PixabayAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val service: PixabayAPI
) {

    suspend fun getImages(q: String) : List<Image>? {
        return withContext(Dispatchers.Default) {
            val response = service.getImage(q= q)
            val processedResponse = processData(response)
            processedResponse?.hits
        }
    }

    private fun <T> processData (response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}