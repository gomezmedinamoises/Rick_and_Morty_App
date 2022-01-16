package com.mgomezm.rickandmortyapp.repository

import com.mgomezm.rickandmortyapp.model.Character
import com.mgomezm.rickandmortyapp.network.RetrofitInstance

class Repository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = RetrofitInstance.apiClient.getCharacterById(characterId)
        if (request.isSuccessful) {
            return request.body()
        }

        return null
    }
}