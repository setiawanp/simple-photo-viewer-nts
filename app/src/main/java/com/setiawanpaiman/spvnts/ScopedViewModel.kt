package com.setiawanpaiman.spvnts

import androidx.lifecycle.ViewModel
import com.setiawanpaiman.spvnts.utils.CoroutineContexts
import kotlinx.coroutines.Job

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
open class ScopedViewModel(protected val coroutineContexts: CoroutineContexts) : ViewModel() {
    private val job = Job()
    protected val scope = job + coroutineContexts.main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}