package com.sakeanduk.www.workcard.Services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.sakeanduk.www.workcard.consts.CommonConst
import com.sakeanduk.www.workcard.helper.WordcardDBHelper
import com.sakeanduk.www.workcard.model.Wordcard

/**
 * register word
 */
fun registerWord(context: Context, question: String, answer: String): Int {
    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase

    // execute insert
    var status: Long
    try {
        val values = ContentValues().apply{
            put(CommonConst.KEY_QUESTION, question)
            put(CommonConst.KEY_ANSWER, answer)
            put(CommonConst.KEY_TESTCOUNT, 0)
            put(CommonConst.KEY_ANSWERCOUNT, 0)
        }
        status = db.insert(CommonConst.TABLE_NAME_WORDCARD, null, values)
    }
    catch (e: Exception) {
        e.printStackTrace()
        status = -1
    }
    finally {
        db.close()
    }
    return status.toInt()
}

/**
 * update word
 */
fun updateWord (context: Context, id: Int, question: String, answer: String): Int {
    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase

    // execute update
    var status: Int
    try {
        val values = ContentValues().apply {
            put(CommonConst.KEY_QUESTION, question)
            put(CommonConst.KEY_ANSWER, answer)
        }
        status = db.update(CommonConst.TABLE_NAME_WORDCARD, values, CommonConst.KEY_ID + CommonConst.WHERE_EQUAL, arrayOf(id.toString()))
    }
    catch (e: Exception) {
        e.printStackTrace()
        status = -1
    }
    finally {
        db.close()
    }
    return status
}

/**
 * updatec count
 */
fun updateCount (context: Context, id: Int, correctFlg: Boolean) : Int{
    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase
    val word = getWordById(context, id)

    // execute update
    var status: Int
    try {
        val values = ContentValues().apply {
            put(CommonConst.KEY_TESTCOUNT, word.testCount + 1)
            put(CommonConst.KEY_ANSWERCOUNT, if (correctFlg) word.answerCount + 1 else word.answerCount )
        }
        status = db.update(CommonConst.TABLE_NAME_WORDCARD, values, CommonConst.KEY_ID + CommonConst.WHERE_EQUAL, arrayOf(id.toString()))
    }
    catch (e: Exception) {
        e.printStackTrace()
        status = -1
    }
    finally {
        db.close()
    }
    return status
}

/**
 * delete word
 */
fun deleteWord (context: Context, id: Int): Int {
    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase

    //execute delete
    var status: Int
    try {
        status = db.delete(CommonConst.TABLE_NAME_WORDCARD, CommonConst.KEY_ID + CommonConst.WHERE_EQUAL, arrayOf(id.toString()))
    }
    catch (e: Exception) {
        e.printStackTrace()
        status = -1
    }
    finally {
        db.close()
    }
    return status
}

fun deletemutipleWords (context: Context, ids: MutableList<Int>): Int {
    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase

    //execute delete
    var status: Int
    try {
        status = db.delete(CommonConst.TABLE_NAME_WORDCARD, CommonConst.KEY_ID + CommonConst.WHERE_IN + "(?)", arrayOf(ids.joinToString()))
    }
    catch (e: Exception) {
        e.printStackTrace()
        status = -1
    }
    finally {
        db.close()
    }
    return status
}

/**
 * get all wordlist
 */
fun getWordListAll(context: Context): List<Wordcard> {
    val wordList: MutableList<Wordcard> = mutableListOf()

    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.readableDatabase

    // execute select
    try {
        val cursor = db.query(CommonConst.TABLE_NAME_WORDCARD, null, null, null, null, null, "_id", null)
        cursor.use { c ->
            while (c.moveToNext()) {
                var word = Wordcard()
                word.id = c.getInt(c.getColumnIndex(CommonConst.KEY_ID))
                word.question = c.getString(c.getColumnIndex(CommonConst.KEY_QUESTION))
                word.answer = c.getString(c.getColumnIndex(CommonConst.KEY_ANSWER))
                word.testCount = c.getInt(c.getColumnIndex(CommonConst.KEY_TESTCOUNT))
                word.answerCount = c.getInt(c.getColumnIndex(CommonConst.KEY_ANSWERCOUNT))
                wordList.add(word)
            }
        }
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
    finally {
        db.close()
    }

    return wordList
}

/**
 * get all wordlist
 */
fun getWordById(context: Context, id: Int): Wordcard {
    val wordList: MutableList<Wordcard> = mutableListOf()

    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.readableDatabase

    // execute select
    try {
        val cursor = db.query(CommonConst.TABLE_NAME_WORDCARD, null, CommonConst.KEY_ID + CommonConst.WHERE_EQUAL, arrayOf(id.toString()), null, null, null, null)
        cursor.use { c ->
            while (c.moveToNext()) {
                var word = Wordcard()
                word.id = c.getInt(c.getColumnIndex(CommonConst.KEY_ID))
                word.question = c.getString(c.getColumnIndex(CommonConst.KEY_QUESTION))
                word.answer = c.getString(c.getColumnIndex(CommonConst.KEY_ANSWER))
                word.testCount = c.getInt(c.getColumnIndex(CommonConst.KEY_TESTCOUNT))
                word.answerCount = c.getInt(c.getColumnIndex(CommonConst.KEY_ANSWERCOUNT))
                wordList.add(word)
            }
        }
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
    finally {
        db.close()
    }

    return wordList.first()
}

/**
 * get all wordlist
 */
fun getSearchWordList(context: Context, keyword: String): List<Wordcard> {
    val wordList: MutableList<Wordcard> = mutableListOf()

    // prepare database
    val helper = WordcardDBHelper(context)
    val db: SQLiteDatabase = helper.readableDatabase

    // execute select
    try {
        val cursor = db.query(CommonConst.TABLE_NAME_WORDCARD, null, CommonConst.KEY_QUESTION + CommonConst.WHERE_LIKE,  arrayOf("%$keyword%"), null, null, "_id", null)
        cursor.use { c ->
            while (c.moveToNext()) {
                var word = Wordcard()
                word.id = c.getInt(c.getColumnIndex(CommonConst.KEY_ID))
                word.question = c.getString(c.getColumnIndex(CommonConst.KEY_QUESTION))
                word.answer = c.getString(c.getColumnIndex(CommonConst.KEY_ANSWER))
                word.testCount = c.getInt(c.getColumnIndex(CommonConst.KEY_TESTCOUNT))
                word.answerCount = c.getInt(c.getColumnIndex(CommonConst.KEY_ANSWERCOUNT))
                wordList.add(word)
            }
        }
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
    finally {
        db.close()
    }
    return wordList
}