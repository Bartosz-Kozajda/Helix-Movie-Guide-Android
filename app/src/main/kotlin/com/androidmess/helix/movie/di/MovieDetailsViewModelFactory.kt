package com.androidmess.helix.movie.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel

class MovieDetailsViewModelFactory(private val schedulers: SchedulersInjector)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(schedulers) as T
    }
}