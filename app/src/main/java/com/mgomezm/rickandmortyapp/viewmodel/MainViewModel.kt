package com.mgomezm.rickandmortyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgomezm.rickandmortyapp.model.Character
import com.mgomezm.rickandmortyapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val repository = Repository()

    private val _characterLiveData = MutableLiveData<Character?>()
    val characterLiveData: LiveData<Character?> = _characterLiveData

    fun getCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterLiveData.postValue(response)
        }
    }
}