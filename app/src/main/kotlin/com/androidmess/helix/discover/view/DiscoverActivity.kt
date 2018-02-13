package com.androidmess.helix.discover.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.ActivityDiscoverBinding
import com.androidmess.helix.discover.presentation.DiscoverViewModel
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : CompositeAppCompatActivity() {

    @Inject
    lateinit var viewModel: DiscoverViewModel

    @Inject
    lateinit var dataAdapter: DiscoverAdapter

    @Inject
    lateinit var layoutManager: GridLayoutManager

    @Inject
    lateinit var onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setupDataBinding()
        setupDataContainer()
        observeData()
    }

    private fun setupDataBinding() {
        val binding: ActivityDiscoverBinding = DataBindingUtil.setContentView(this, R.layout.activity_discover)
        binding.viewModel = viewModel
    }

    private fun setupDataContainer() {
        // FIXME Move to data binding
        onScrolledToBottomDetector
                .scrollEvents(discoverDataContainer.scrollEvents())
                .observe()
                .subscribe {
                    viewModel.scroll.notifyChange()
                }
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
    }

    private fun observeData() {
        viewModel.moviesData.subscribe({
            dataAdapter.addMovie(it)
        })
        viewModel.startFetchingData()
    }
}