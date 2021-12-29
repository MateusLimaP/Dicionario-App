package com.mateuslima.dicionarioclean.core.util

import androidx.appcompat.widget.SearchView

inline fun SearchView.onTextSubmit(crossinline search: (String) -> Unit){
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String): Boolean {
           search.invoke(query)
           return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
           return false
        }

    })
}