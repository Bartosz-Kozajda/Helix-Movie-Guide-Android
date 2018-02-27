package com.androidmess.helix.movie.viewmodel

import android.arch.lifecycle.ViewModel
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.model.data.MovieViewData

class MovieDetailsViewModel(private val schedulers: SchedulersInjector) : ViewModel() {


    fun startFetchingMovie(movie: MovieViewData) {

    }

}