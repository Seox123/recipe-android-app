package com.example.recipe.data

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors

internal class RequestTest {

    private lateinit var testRequest: Request
    private lateinit var testUrl: String
    private lateinit var myCallable: Callable<Array<JSONObject>>

    @Before
    fun setUp() {
        testUrl = "www.themealdb.com/api/json/v1/1/random.php"
        testRequest = Request(testUrl)
        myCallable = object : Callable<Array<JSONObject>> {
            override fun call(): Array<JSONObject> {
                return testRequest.run()
            }
        }
    }

    @Test
    fun run() {
        assertArrayEquals(emptyArray<JSONObject>(),
            Executors.newSingleThreadExecutor().submit(myCallable).get())
    }
}