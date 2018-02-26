package com.androidmess.helix.movie.di

import android.app.Activity
import com.androidmess.helix.movie.view.MovieDetailsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(MovieDetailsActivityComponent::class))
internal abstract class MovieDetailsActivityBinder {

    @Binds
    @IntoMap
    @ActivityKey(MovieDetailsActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: MovieDetailsActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>
}