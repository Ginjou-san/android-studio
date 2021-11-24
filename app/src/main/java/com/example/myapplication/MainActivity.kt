package com.example.myapplication

import android.app.Person
import android.content.Context
import android.icu.text.CaseMap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json = readJSONFromAsset() ?: return
        val film: Response = Gson().fromJson(json, Response::class.java)

//        val obj = JSONObject(getJSONFromAssets()!!)



        Log.i("test", film.items.size.toString())
    }
        private fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("topFilm.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
//    data class Film(@SerializedName("id") val id: String, val rank: Int, val title: String)

    data class Topic(
        @SerializedName("id") val id: Long,
        @SerializedName("rank") val rank: Int,
        @SerializedName("title") val title: String,
        @SerializedName("description") val FullTitle: String

    )









}
class Response (
    val items: List <TopFilm>
)
data class TopFilm(
    val id: String,
    val rank: Int,
    val title: String,
    val FullTitle: String,
    val year: Int,
    val image: String,
    val crew: String,
    val imDbRating: Float ,
    val imDbRatingCount: Int
)

//        val jsonStr = """
//    {
//        "id": "",
//        "rank": ,
//        "title": "",
//        "FullTitle":"",
//        "year": ,
//        "image": "" ,
//        "crew" : "",
//        "imDbRating": ,
//        "imDbRatingCount":
//    }
//    """.trimIndent()
//    class MyModel {
//        @SerializedName("id")
//        var id: ArrayList<MyObject>? = null
//
//        class MyObject {
//            @SerializedName("rank")
//            var rank: String? = null
//
//            @SerializedName("title")
//            var title = 0
//        }
//    }



