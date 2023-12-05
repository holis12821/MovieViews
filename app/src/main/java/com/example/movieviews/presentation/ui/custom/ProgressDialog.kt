package com.example.movieviews.presentation.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.movieviews.R

class ProgressDialog(
    context: Context
): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_dialog)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}