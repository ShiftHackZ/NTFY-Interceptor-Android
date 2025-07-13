package com.shifthackz.android.ntfy.interceptor.ui.screen.notifications

import androidx.lifecycle.ViewModel
import com.shifthackz.android.ntfy.interceptor.repository.NotificationsRepository

class NotificationsViewModel(notificationsRepository: NotificationsRepository) : ViewModel() {
    val pushNotifications = notificationsRepository.observe()
}
