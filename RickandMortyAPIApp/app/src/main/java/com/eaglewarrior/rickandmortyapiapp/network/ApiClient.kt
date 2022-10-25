package com.eaglewarrior.rickandmortyapiapp.network

import com.eaglewarrior.rickandmortyapiapp.network.response.GetCharacterByIdResponse
import retrofit2.Response

/**
 * Created by Clarence E Moore on 2022-10-15.
 *
 * Description:
 *
 *
 */
class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {

        return try {
            SimpleResponse.success(apiCall.invoke())

        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}
