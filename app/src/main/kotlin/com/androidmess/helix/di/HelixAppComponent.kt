package com.androidmess.helix.di

import com.androidmess.helix.HelixApp
import com.androidmess.helix.common.network.di.NetworkModule
import com.androidmess.helix.discover.di.DiscoverActivityBinder
import com.androidmess.helix.discover.model.di.DiscoverModelModule
import com.androidmess.helix.movie.di.MovieDetailsActivityBinder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        HelixAppModule::class,
        NetworkModule::class,
        DiscoverModelModule::class,
        DiscoverActivityBinder::class,
        MovieDetailsActivityBinder::class
))
interface HelixAppComponent {

    fun inject(application: HelixApp): HelixApp
}