package com.sakeanduk.www.workcard.adapter

import android.content.Context
import android.view.*
import android.widget.BaseAdapter
import android.widget.TextView
import com.sakeanduk.www.workcard.R
import com.sakeanduk.www.workcard.model.Wordcard
import kotlinx.android.synthetic.main.list_words_row.view.*

class WordAdapter(private val context: Context, private val words: List<Wordcard>) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getItem(Target: Int) = words[Target]

    override fun getItemId(Target: Int) = words[Target].id.toLong()

    override fun getCount() = words.size

    override fun getView(target: Int, convertView: View?, parent: ViewGroup?): View {
        // in case convertView exists, use it. in case of no convertView, create new one.
        val view = convertView ?: createView(parent)

        // get target
        val targetWord = getItem(target)

        //get viewholder by tag
        val viewHolder = view.tag as ViewHolder

        //set words
        viewHolder.name.text = if (targetWord.answerFlg) targetWord.answer else targetWord.question
        changeAnswerFlg(target)

        return view
    }

    private fun changeAnswerFlg (target: Int) {
        words[target].answerFlg = !(words[target].answerFlg)
    }

    private fun createView(parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_words_row, parent, false)
            view.tag = ViewHolder(view)
        return view
    }

    private class ViewHolder(view: View) {
        val name = view.findViewById<TextView>(R.id.wordRow)
    }

}