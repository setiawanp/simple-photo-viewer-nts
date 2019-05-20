package com.setiawanpaiman.spvnts.fakes

import com.setiawanpaiman.spvnts.utils.CoroutineContexts
import kotlinx.coroutines.Dispatchers

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
object CoroutineContextsFakes {

    val TEST = CoroutineContexts(Dispatchers.Unconfined, Dispatchers.Unconfined)
}