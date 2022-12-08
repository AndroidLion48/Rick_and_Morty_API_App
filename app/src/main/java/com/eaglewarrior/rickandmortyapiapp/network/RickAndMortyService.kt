package com.eaglewarrior.rickandmortyapiapp.network


import com.eaglewarrior.rickandmortyapiapp.network.response.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Clarence E Moore on 2022-10-14.
 *
 * Description:
 *
 *
 */
interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>
}
