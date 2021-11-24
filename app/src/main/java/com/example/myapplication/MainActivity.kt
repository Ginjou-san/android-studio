package com.example.myapplication

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Context
import android.media.Image
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.annotations.SerializedName
import org.json.JSONObject


import java.io.InputStream

class ResponseList : ArrayList<Tittle>()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listType = object : TypeToken<List<Tittle>>() {}.type

//        val json = readJSONFromAsset() ?: return
//        val film: Response = Gson().fromJson(json, Response::class.java)
//        Log.i("test", film.items.size.toString())
//
        val json1 = readJSONFromAsset()
        if (json1 != null)
        {


            val dadata: ResponseList = Gson().fromJson(json1, ResponseList::class.java)
            Log.i("test2", "${dadata.size}")
            dadata.forEachIndexed { index, item ->
//                Log.i("test2", "${item.id.name} ")
            }
        }
    }
        private fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("title.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}

data class Tittle (
    val id: String,
    val title: String,
    val originalTitle: String,
    val fullTitle: String,
    val type: String,
    val year: String,
    val image: String,
    val releaseDate: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val plotLocal: String,

//    @Json(name = "plotLocalIsRtl")
//    val plotLocalIsRTL: Boolean,

    val awards: String,
    val directors: String,
    val directorList: List<CompanyListElement>,
    val writers: String,
    val writerList: List<CompanyListElement>,
    val stars: String,
    val starList: List<CompanyListElement>,
    val actorList: List<ActorList>,
    val fullCast: Any? = null,
    val genres: String,
    val genreList: List<CountryListElement>,
    val companies: String,
    val companyList: List<CompanyListElement>,
    val countries: String,
    val countryList: List<CountryListElement>,
    val languages: String,
    val languageList: List<CountryListElement>,
    val contentRating: String,

//    @Json(name = "imDbRating")
//    val imDBRating: String,
//
//    @Json(name = "imDbRatingVotes")
//    val imDBRatingVotes: String,

    val metacriticRating: String,
    val ratings: Any? = null,
    val wikipedia: Any? = null,
    val posters: Any? = null,
    val images: Any? = null,
    val trailer: Any? = null,
    val boxOffice: BoxOffice,
    val tagline: String,
    val keywords: String,
    val keywordList: List<String>,
    val similars: List<Similar>,
    val tvSeriesInfo: Any? = null,
    val tvEpisodeInfo: Any? = null,
    val errorMessage: String
)

data class ActorList (
    val id: String,
    val image: String,
    val name: String,
    val asCharacter: String
)

data class BoxOffice (
    val budget: String,
    val openingWeekendUSA: String,
    val grossUSA: String,
    val cumulativeWorldwideGross: String
)

data class CompanyListElement (
    val id: String,
    val name: String
)

data class CountryListElement (
    val key: String,
    val value: String
)

data class Similar (
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val plot: String,
    val directors: String,
    val stars: String,
    val genres: String,
//
//    @Json(name = "imDbRating")
//    val imDBRating: String
)

//data class Fio(
//    val value: String,
//    @SerializedName("unrestricted_value")  val unrestricted: String,
//    val data: Data
//)
//data class Data(
//    val surname: String,
//    val name: String,
//    val patronymic: String,
//    val gender: String
//)


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




