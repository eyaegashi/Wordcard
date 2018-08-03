package com.sakeanduk.www.workcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddWordsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_words)
    }

    private inner class ImplementedListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if (v != null){
                when (v.id) {
                    R.id.addWords -> moveAddWorkds()
                }
            }
        }

        fun moveAddWorkds() {
            val intent = Intent(this@AddWordsPage, AddWordsPage::class.java)
            startActivity(intent)
        }
    }
}
