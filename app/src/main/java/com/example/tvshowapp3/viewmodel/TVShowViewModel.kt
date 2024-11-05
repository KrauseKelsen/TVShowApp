package com.example.tvshowapp3.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowapp3.models.TvShowItem
import com.example.tvshowapp3.repository.TVShowRepository

//TVShowViewModel --> repository: TVShowRepository --> getAllTVShows(): ApiService
@HiltViewModel
class TVShowViewModel

    @Inject
    constructor(private val repository: TVShowRepository) : ViewModel() {

        private val _response = MutableLiveData<List<TvShowItem>>()
        val responseTVShow: LiveData<List<TvShowItem>> get() = _response

        init {
            getAllTVShows()
        }

        private fun getAllTVShows() = viewModelScope.launch {
            repository.getTVShows().let { response ->
                //IF start
                if (response.isSuccessful){
                    _response.postValue(response.body())
                }else{
                    Log.d("tag", "getAllTVShows Error: ${response.code()}")
                }
                //IF end
            }
        }
    }