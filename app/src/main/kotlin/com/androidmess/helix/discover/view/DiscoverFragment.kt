package com.androidmess.helix.discover.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.DiscoverFragmentBinding
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.releaseContext

class DiscoverFragment : Fragment() {

    companion object {
        const val CONTEXT_NAME = "DiscoverFragment"
        fun newInstance() = DiscoverFragment()
    }

    val discoverViewModel: DiscoverViewModel by viewModel()
    val dataAdapter: DiscoverAdapter by inject()
    val discoverLayoutManager: LinearLayoutManager by inject()
    val onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector by inject()
    var binding: DiscoverFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO Create databinding fragments plugins
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_fragment, container, false)
        binding?.run {
            setVariable(BR.viewModel, discoverViewModel)
            setVariable(BR.adapter, dataAdapter)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataContainer(binding?.discoverDataContainer)
        discoverViewModel.viewReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO Create DI fragments plugins
        releaseContext(CONTEXT_NAME)
    }

    // FIXME Move to data binding
    private fun setupDataContainer(discoverDataContainer: RecyclerView?) {
        discoverDataContainer?.run {
            onScrolledToBottomDetector
                .scrollEvents(scrollEvents())
                .observe()
                .subscribe {
                    discoverViewModel.scroll.notifyChange()
                }
            setHasFixedSize(true)
            layoutManager = discoverLayoutManager
            adapter = dataAdapter
        }
    }
}