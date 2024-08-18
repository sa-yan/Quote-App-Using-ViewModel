package com.sayan.quotesappusingvm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList : Array<QuoteModel> = emptyArray()
    var index = 0

    init {
        quoteList = loadQuotes()
    }

    private fun loadQuotes(): Array<QuoteModel> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)

        val gson = Gson()
        return gson.fromJson(json, Array<QuoteModel>::class.java)
    }

     fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]
}