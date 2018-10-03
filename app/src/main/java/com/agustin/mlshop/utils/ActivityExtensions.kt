package com.agustin.mlshop.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.agustin.mlshop.BaseActivity
import com.agustin.mlshop.customView.NotesDialogHelper

inline fun BaseActivity.showDialog(func: NotesDialogHelper.() -> Unit): AlertDialog =
        NotesDialogHelper(this).apply {
            func()
        }.create()

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
