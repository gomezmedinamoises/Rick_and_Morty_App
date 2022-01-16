package com.mgomezm.rickandmortyapp

import com.mgomezm.rickandmortyapp.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(@Path("character-id") characterId: Int): Response<Character>
}