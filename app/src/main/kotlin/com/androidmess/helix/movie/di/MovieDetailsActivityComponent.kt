package com.androidmess.helix.movie.di

import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.movie.view.MovieDetailsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = arrayOf(
                MovieDetailsActivityModule::class
        )
)
interface MovieDetailsActivityComponent : AndroidInjector<MovieDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MovieDetailsActivity>()
}