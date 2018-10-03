package com.agustin.mlshop.customView

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.agustin.mlshop.BaseDialogHelper
import com.agustin.mlshop.R

class NotesDialogHelper(val context: Context, val pickUpAtStoreAvailable: Boolean? = false) : BaseDialogHelper() {

    override val dialogView: View
        get() = LayoutInflater.from(context).inflate(R.layout.shipping_dialog, null)
    override val builder: AlertDialog.Builder
        get() = AlertDialog.Builder(context).setView(dialogView)
}