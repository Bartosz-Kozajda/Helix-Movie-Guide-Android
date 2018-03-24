package com.androidmess.helix.discover.di

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.app.getScreenWidth
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewItemSizeCalculator
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.discover.presentation.DiscoverViewModel
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverActivity
import com.androidmess.helix.discover.view.DiscoverAdapter
import dagger.Module
import dagger.Provides

@Module
class DiscoverActivityModule {

    @ActivityScope
    @Provides
    fun providesDiscoverViewModelFactory(
        schedulersInjector: SchedulersInjector,
        getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
    ): DiscoverViewModelFactory {
        return DiscoverViewModelFactory(schedulersInjector, getDiscoverMoviesUseCase)
    }

    @ActivityScope
    @Provides
    fun providesDiscoverViewModel(
        activity: DiscoverActivity,
        factory: DiscoverViewModelFactory
    ): DiscoverViewModel {
        return ViewModelProviders.of(activity, factory).get(DiscoverViewModel::class.java)
    }

    @ActivityScope
    @Provides
    fun provideRecyclerViewItemSizeCalculator(context: Context): RecyclerViewItemSizeCalculator {
        val calculator = RecyclerViewItemSizeCalculator(context.getScreenWidth())
        calculator.spanCount = context.resources.getInteger(R.integer.discover_view_span_count)
        return calculator
    }

    @ActivityScope
    @Provides
    fun provideRecyclerViewOnScrolledToBottomDetector(
        schedulersInjector: SchedulersInjector,
        layoutManager: GridLayoutManager
    ): RecyclerViewOnScrolledToBottomDetector {
        return RecyclerViewOnScrolledToBottomDetector(schedulersInjector, layoutManager)
    }

    @ActivityScope
    @Provides
    fun provideAdapter(itemSizeCalculator: RecyclerViewItemSizeCalculator): DiscoverAdapter {
        return DiscoverAdapter(itemSizeCalculator)
    }

    @ActivityScope
    @Provides
    fun provideLayoutManager(context: Context): GridLayoutManager {
        return GridLayoutManager(
            context,
            context.resources.getInteger(R.integer.discover_view_span_count)
        )
    }
}