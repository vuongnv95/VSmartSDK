package com.example.baseproject.ui.splash

import android.os.Handler
import android.os.Looper
import com.example.core.base.BaseViewModel
import com.example.core.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val DURATION_SPLASH = 1000L

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    val actionSPlash = SingleLiveEvent<SplashActionState>()

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = Runnable {
        actionSPlash.value = SplashActionState.Finish
    }

    init {
        handler.postDelayed(runnable, DURATION_SPLASH)
    }

    override fun onCleared() {
        handler.removeCallbacks(runnable)
        super.onCleared()
    }
}

sealed class SplashActionState {
    object Finish : SplashActionState()
}