package com.example.tvshowapp3.repository

import com.example.tvshowapp3.api.ApiService
import javax.inject.Inject

class TVShowRepository
    @Inject
    constructor(private val apiService: ApiService){
        suspend fun getTVShows() = apiService.getTVShows()
    }