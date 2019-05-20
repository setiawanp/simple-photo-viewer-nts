package com.setiawanpaiman.spvnts.ui.thumbnail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.setiawanpaiman.spvnts.R
import com.setiawanpaiman.spvnts.models.Status
import com.setiawanpaiman.spvnts.ui.detail.PhotoDetailActivity
import com.setiawanpaiman.spvnts.widget.SpacingItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ThumbnailActivity : AppCompatActivity(), ThumbnailAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var adapter: ThumbnailAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, vmFactory)
            .get(ThumbnailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        getPhotos()
    }

    override fun onRefresh() {
        getPhotos()
    }

    override fun onClick(pos: Int) {
        val photoUrl = adapter.data[pos].url
        startActivity(PhotoDetailActivity.newIntent(this, photoUrl))
    }

    private fun showLoading(visible: Boolean) {
        swipeRefresh.post {
            swipeRefresh.isRefreshing = visible
        }
    }

    private fun setupView() {
        adapter = ThumbnailAdapter(this)
        swipeRefresh.setOnRefreshListener(this)
        recyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_small)))
        recyclerView.layoutManager = GridLayoutManager(this, COLUMN_COUNT, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun getPhotos() {
        viewModel.getPhotos().observe(this, Observer { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    adapter.setData(resource.data)
                    showLoading(false)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Snackbar.make(swipeRefresh, R.string.error_message, Snackbar.LENGTH_SHORT)
                        .show()
                }
                Status.LOADING -> {
                    showLoading(true)
                }
            }
        })
    }

    companion object {
        const val COLUMN_COUNT = 3
    }
}
