package com.lotta.itunesapi.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.lotta.itunesapi.R
import javax.inject.Inject

class LoadingDialog @Inject constructor(
   context : Context
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        val view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        setContentView(view)
    }
}
