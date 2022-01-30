package com.example.movieviews.presentation.ui.utils

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor

object InstantRuleExecution {
    fun onStart() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean = true

        })
    }

    fun onStop() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}