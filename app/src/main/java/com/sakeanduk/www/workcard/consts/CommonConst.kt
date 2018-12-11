package com.sakeanduk.www.workcard.consts

class CommonConst() {
    companion object {
        //DB values
        const val TABLE_NAME_WORDCARD = "wordcard"
        const val KEY_ID = "_id"
        const val KEY_QUESTION = "question"
        const val KEY_ANSWER = "answer"
        const val KEY_TESTCOUNT = "testCount"
        const val KEY_ANSWERCOUNT = "answerCount"
        const val WHERE_EQUAL = " = ?"
        const val WHERE_LIKE = " LIKE ?"
        const val WHERE_IN = " IN (?)"

        // fragment setting
        const val FRAGMENT_ARGUMENTS_KEY_TYPE = "Type"
        const val FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_ADD = "add"
        const val FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_FAILED = "Fail"
        const val FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_UPDATE = "update"
        const val FRAGMENT_ARGUMENTS_KEY_TYPE_VALUE_DELETE = "delete"

        // parameter values
        const val PARAM_ID = "id"
        const val PARAM_QUESTION = "question"
        const val PARAM_ANSWER = "answer"
    }
}