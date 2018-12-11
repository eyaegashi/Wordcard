package com.sakeanduk.www.workcard.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import com.sakeanduk.www.workcard.R
import com.sakeanduk.www.workcard.consts.CommonConst

/**
 *  fragment class for common complete dialog
 */
class DeleteConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val bundle = arguments

        // set title and message depends on page
        when (bundle.getString(CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE)) {
            CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_ADD -> {
                builder.setTitle(R.string.dialog_title_registComp)
                builder.setMessage(R.string.dialog_msg_registComp)
            }
            CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_UPDATE -> {
                builder.setTitle(R.string.dialog_title_updateComp)
                builder.setMessage(R.string.dialog_msg_updateComp)
            }
            CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_FAILED -> {
                builder.setTitle(R.string.dialog_title_updateComp)
                builder.setMessage(R.string.dialog_msg_updateComp)
            }
            else -> {
                builder.setTitle("")
                builder.setMessage("")
            }
        }

        //set ok button
        builder.setPositiveButton(R.string.button_ok, DialogButtonClickListener())

        return builder.create()
    }

    /**
     * dialog OK button click Listener
     */
    private inner class DialogButtonClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            if (dialog != null) {
                dialog.dismiss()
                Toast.makeText(activity, "", Toast.LENGTH_LONG).show()
            }
        }
    }
}
