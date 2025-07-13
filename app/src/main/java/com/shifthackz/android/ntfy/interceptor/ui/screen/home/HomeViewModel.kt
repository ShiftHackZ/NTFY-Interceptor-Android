package com.shifthackz.android.ntfy.interceptor.ui.screen.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _route = MutableStateFlow(HomeRoute.Notifications)
    val route = _route.asStateFlow()

    fun navigate(route: HomeRoute) {
        _route.value = route
    }
}
