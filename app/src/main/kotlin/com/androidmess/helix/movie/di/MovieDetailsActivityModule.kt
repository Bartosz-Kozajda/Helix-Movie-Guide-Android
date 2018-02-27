package com.androidmess.helix.movie.di

import android.arch.lifecycle.ViewModelProviders
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import com.androidmess.helix.movie.view.MovieDetailsActivity
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsActivityModule {

    @ActivityScope
    @Provides
    fun providesMovieDetailsViewModelFactory(schedulersInjector: SchedulersInjector,
                                             getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                             l: L): MovieDetailsViewModelFactory {
        return MovieDetailsViewModelFactory(schedulersInjector, getMovieDetailsUseCase, l)
    }

    @ActivityScope
    @Provides
    fun providesMovieDetailsViewModel(activity: MovieDetailsActivity, factory: MovieDetailsViewModelFactory): MovieDetailsViewModel {
        return ViewModelProviders.of(activity, factory).get(MovieDetailsViewModel::class.java)
    }
}