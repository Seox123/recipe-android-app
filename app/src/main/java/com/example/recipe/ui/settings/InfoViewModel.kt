package com.example.recipe.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Feel free to click the refresh button to get a new random recipe.\n" +
                "\nThe search function allows you to find recipes that have those " +
                "letters in their name.\n" +
                "\nClick on the images to go to the recipe source links!"
    }
    val text: LiveData<String> = _text
}