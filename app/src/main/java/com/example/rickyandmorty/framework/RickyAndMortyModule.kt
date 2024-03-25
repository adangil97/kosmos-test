package com.example.rickyandmorty.framework

import com.example.rickyandmorty.data.RickyAndMortyRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class RickyAndMortyModule {

    @Binds
    abstract fun rickyAndMortyRemoteDataSource(
        rickyAndMortyApiDataSource: RickyAndMortyApiDataSource
    ): RickyAndMortyRemoteDataSource

    companion object {
        @Provides
        fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        fun rickyAndMortyService(
            retrofit: Retrofit
        ): RickyAndMortyService = retrofit.create(RickyAndMortyService::class.java)
    }
}