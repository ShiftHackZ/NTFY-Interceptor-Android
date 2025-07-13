package com.shifthackz.android.ntfy.interceptor.ui.screen.log

import androidx.lifecycle.ViewModel
import com.shifthackz.android.ntfy.interceptor.repository.LogRepository

class LogViewModel(logRepository: LogRepository) : ViewModel() {
    val logs = logRepository.observe()
}
