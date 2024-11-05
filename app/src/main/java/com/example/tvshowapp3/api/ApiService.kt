package com.example.tvshowapp3.api

import com.example.tvshowapp3.helper.Constants
import com.example.tvshowapp3.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getTVShows():Response<TvShowResponse>
}