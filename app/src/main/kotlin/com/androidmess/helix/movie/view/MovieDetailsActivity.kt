package com.androidmess.helix.movie.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.discover.model.data.MovieViewData
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieDetailsActivity : CompositeAppCompatActivity() {

    @Inject
    lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        val movie = intent.extras.getParcelable<MovieViewData>(INTENT_MOVIE_DETAILS)
        setupDataBinding(movie)
        super.onCreate(savedInstanceState)
        viewModel.startFetchingMovie(movie)
    }

    private fun setupDataBinding(movie: MovieViewData) {
        val plugin = DataBindingActivityPlugin(this, viewModel, R.layout.activity_movie_details)
        plugin.addBinding(BR.movie, movie)
        registerPlugin(plugin)
    }
}

private const val INTENT_MOVIE_DETAILS = "INTENT_MOVIE_DETAILS";

fun Activity.movieDetailsIntent(movieViewData: MovieViewData): Intent {
    return Intent(this, MovieDetailsActivity::class.java).apply {
        putExtra(INTENT_MOVIE_DETAILS, movieViewData)
    }
}