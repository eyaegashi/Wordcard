package com.sakeanduk.www.workcard.model

data class Wordcard (
        var id: Int = 0,
        var question: String = "",
        var answer: String = "",
        var answerFlg: Boolean = false,
        var testCount: Int = 0,
        var answerCount: Int = 0)
