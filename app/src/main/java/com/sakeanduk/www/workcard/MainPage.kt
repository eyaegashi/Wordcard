package com.sakeanduk.www.workcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main_page.view.*

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val addWordBtn: Button = findViewById(R.id.addWords)
        addWordBtn.setOnClickListener(ImplementedListener())
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
            val intent = Intent(this@MainPage, AddWordsPage::class.java)
            startActivity(intent)
        }
    }
}
