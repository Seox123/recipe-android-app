package com.example.recipe.ui.search

import androidx.lifecycle.ViewModel
import com.example.recipe.data.RecipeAdapter
import com.example.recipe.data.Request
import org.json.JSONObject
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class SearchViewModel : ViewModel() {

    fun getAdapter(url: String): RecipeAdapter {
        val myCallable = object : Callable<Array<JSONObject>> {
            override fun call(): Array<JSONObject> {
                return Request(url).run()
            }
        }
        val myArray: Array<JSONObject> =
            Executors.newSingleThreadExecutor().submit(myCallable).get()

        return RecipeAdapter(
            myArray
        )
    }
}