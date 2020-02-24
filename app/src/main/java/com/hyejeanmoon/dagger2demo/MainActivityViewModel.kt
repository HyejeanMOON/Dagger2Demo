package com.hyejeanmoon.dagger2demo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainActivityViewModel(private val name: Name, private val context: Context) :
    ViewModel() {

    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData: LiveData<String> get() = _nameLiveData

    init {
        _nameLiveData.value = name.n
    }

    fun setName(n: String) {
        name.n = n
        _nameLiveData.value = name.n
        if (n.toInt() % 3 == 0) {
            Toast.makeText(context, "hello world!", Toast.LENGTH_LONG).show()
        }
    }


    class Factory @Inject constructor(
        private val name: Name,
        @ApplicationContext private val context: Context
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == MainActivityViewModel::class.java) {
                return MainActivityViewModel(name, context) as T
            }
            val className = modelClass.name
            throw IllegalArgumentException("Unknown ViewModel class : $className")
        }
    }
}
