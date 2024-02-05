package com.example.kmptranslator.translate.data

fun interface Mapper<SOURCE, RESULT> {

    fun map(source: SOURCE): RESULT

    fun mapList(source: List<SOURCE>): List<RESULT> = source.map { map(it) }
}