package com.sakeanduk.www.workcard.Fragment

import android.app.*
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.sakeanduk.www.workcard.R
import com.sakeanduk.www.workcard.activity.ManageWordsPageActivity
import com.sakeanduk.www.workcard.consts.CommonConst

/**
 *  fragment class for common complete dialog
 */
class CompleteDialogFragment : DialogFragment() {
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
            CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_DELETE -> {
                builder.setTitle(R.string.dialog_title_deleteComp)
                builder.setMessage(R.string.dialog_msg_deleteComp)
            }
            CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_FAILED -> {
                builder.setTitle(R.string.dialog_title_failed)
                builder.setMessage(R.string.dialog_msg_failed)
            }
            else -> {
                    builder.setTitle("")
                    builder.setMessage("")
            }
        }

        //set ok button
        builder.setPositiveButton(R.string.button_ok, DialogButtonClickListener(bundle.getString(CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE)))

        return builder.create()
    }

    /**
     * dialog OK button click Listener
     */
    private inner class DialogButtonClickListener(private val type: String) : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            dialog?.dismiss()

            // in case update, back to managewordPage
            if (type == CommonConst.FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_UPDATE) {
                val intent = Intent(context, ManageWordsPageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

