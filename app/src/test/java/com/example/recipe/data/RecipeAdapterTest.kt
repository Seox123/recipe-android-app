package com.example.recipe.data

import org.json.JSONObject
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class RecipeAdapterTest {

    private lateinit var testRecipeAdapter0: RecipeAdapter
    private lateinit var testRecipeAdapter1: RecipeAdapter
    private lateinit var myArray: Array<JSONObject>

    @Before
    fun setUp() {
        myArray = arrayOf(JSONObject("""{"a": 1}"""), JSONObject("""{"a": 1}"""),
            JSONObject("""{"a": 1}"""), JSONObject("""{"a": 1}"""))
        testRecipeAdapter0 = RecipeAdapter(emptyArray())
        testRecipeAdapter1 = RecipeAdapter(myArray)
    }

    @Test
    fun getItemCount() {
        assertEquals(0, testRecipeAdapter0.itemCount)
        assertEquals(4, testRecipeAdapter1.itemCount)
    }
}