package com.mgomezm.rickandmortyapp.network

import com.mgomezm.rickandmortyapp.model.Character
import com.mgomezm.rickandmortyapp.RickAndMortyService
import retrofit2.Response

class APIClient(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): Response<Character> {
        return rickAndMortyService.getCharacterById(characterId)
    }
}