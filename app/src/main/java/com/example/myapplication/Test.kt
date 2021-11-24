package com.example.top250

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Context
import com.example.myapplication.R
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.InputStream
import java.net.URL

//data class Person(val query: String, val count: Int)
//class Response(
//    val items: List<Film>
//)
class Response1(
    val item: List<Fio>
)

class ResponseList : ArrayList<Fio>()

//data class Film(
//        val id: String,
//        val rank: Int,
//        val title: String,
//        val fullTitle: String,
//        val year: Int,
//        val image: String,
//        val crew: String,
//        val imDbRating: Float,
//        val imDbRatingCount: Long
//        )

data class Fio(
    val value: String,
    @SerializedName("unrestricted_value")  val unrestricted: String,
    val data: Data
)
data class Data(
    val surname: String,
    val name: String,
    val patronymic: String,
    val gender: String

)




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listType = object : TypeToken<List<Fio>>() {}.type

//        val json = readJSONFromAsset()
//        if (json != null)
//        {
//            val top250: Response = Gson().fromJson(json, Response::class.java)
//           Log.i("test1", top250.items.toString())
//        }
        val json1 = readJSONFromAsset1()
        if (json1 != null)
        {

            val dadata: ResponseList = Gson().fromJson(json1, ResponseList::class.java)
            Log.i("test2", "${dadata.size}")
            dadata.forEachIndexed { index, item ->
                Log.i("test2", "${item.data.name} ${item.data.gender}")
            }
//            Log.w("test2", dadata.item.size.toString())
        }
    }
    //    fun readJSONFromAsset(): String? {
//        var json: String? = null
//        try {
//            val  inputStream:InputStream = assets.open("test.json")
//            json = inputStream.bufferedReader().use{it.readText()}
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//
//            return null
//        }
//        return json
//
//    }
    fun readJSONFromAsset1(): String? {
        var json: String? = null
        try {
            val  inputStream:InputStream = assets.open("dadata.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()

            return null
        }
        return json

    }
}