package com.example.tvshowapp3.di

import com.example.tvshowapp3.api.ApiService
import com.example.tvshowapp3.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Anotación que indica que la clase AppModule es un módulo de Dagger Hilt.
// Un módulo es responsable de proporcionar instancias de clases que se inyectan en otras partes de la aplicación.
@Module
//Anotación que indica que el módulo debe ser instalado en el componente SingletonComponent.
// Esto significa que las instancias proporcionadas por este módulo tendrán un ámbito de singleton,
// es decir, solo habrá una instancia única para toda la duración de la aplicación.
@InstallIn(SingletonComponent::class)
object AppModule {
    //Anotación que indica que el método proporciona una dependencia.
    //Dagger Hilt utilizará este método para crear e inyectar instancias de las dependencias necesarias.
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
}