package com.androidmess.helix.movie.model.di

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.common.network.NetworkConfig
import com.androidmess.helix.movie.model.repository.RetrofitMovieDetailsRepository
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MovieDetailsModelModule {

    @Provides
    @Singleton
    fun providesRepositoryMovieDetails(retrofit: Retrofit, networkConfig: NetworkConfig): Repository.MovieDetails {
        return RetrofitMovieDetailsRepository(retrofit, networkConfig.apiKey)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: Repository.MovieDetails): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}