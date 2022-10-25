package com.eaglewarrior.rickandmortyapiapp

import com.eaglewarrior.rickandmortyapiapp.network.NetworkLayer
import com.eaglewarrior.rickandmortyapiapp.network.response.GetCharacterByIdResponse

/**
 * Created by Clarence E Moore on 2022-10-15.
 *
 * Description:
 *
 *
 */
class SharedRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }
}
