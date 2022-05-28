package com.example.recipe.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.recipe.data.RecipeAdapter
import com.example.recipe.data.Request
import org.json.JSONObject
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val url = "https://www.themealdb.com/api/json/v1/1/random.php"
    fun getAdapter(): RecipeAdapter {
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