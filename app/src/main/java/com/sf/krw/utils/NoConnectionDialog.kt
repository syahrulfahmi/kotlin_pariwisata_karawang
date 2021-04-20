package com.sf.krw.utils

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.sf.krw.R

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

class NoConnectionDialog {

    companion object {

        fun show(
            context: Context,
            positiveButton: String,
            negativeButton: String? = null,
            positiveOnClick: DialogInterface.OnClickListener? = null,
            negativeOnClick: DialogInterface.OnClickListener? = null,
            onDismiss: DialogInterface.OnDismissListener? = null,
            cancellable: Boolean = false
        ) {

            val builder = AlertDialog.Builder(context, R.style.AlertDialogThemeTransparant)
            with(builder)
            {
                setPositiveButton(positiveButton, positiveOnClick)
                negativeButton?.apply { setNegativeButton(this, negativeOnClick) }
                onDismiss?.apply { setOnDismissListener(onDismiss) }
                setCancelable(cancellable)
                setView(R.layout.dialog_no_connection)
            }

            val dialog = builder.create()
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.show()

            val btnTryConnect = dialog.findViewById<AppCompatButton>(R.id.btnTryConnect)

            btnTryConnect?.text = positiveButton
            btnTryConnect?.setOnClickListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).callOnClick()
            }

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).visibility = View.GONE

            val metrics = context.resources.displayMetrics
            val width = metrics.widthPixels
            dialog.window?.setLayout(6 * width / 7, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}