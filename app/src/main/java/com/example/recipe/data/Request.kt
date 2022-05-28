package com.example.recipe.data

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.URL

class Request(private val url: String) {

    fun run(): Array<JSONObject> {
        try {
            val obj = JSONObject(URL(url).readText()).optJSONArray("meals")
            if (obj != null) {
                val list = Array<JSONObject>(obj.length()) {
                    obj.getJSONObject(it)
                }
                return list;
            }
            return emptyArray()
        } catch (e: MalformedURLException) {
            return emptyArray()
        }
    }
}