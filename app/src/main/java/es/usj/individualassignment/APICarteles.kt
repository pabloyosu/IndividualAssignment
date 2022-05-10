package es.usj.individualassignment

import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APICarteles {
    fun getData(title: String):String{
        val urlConnection = URL("http://www.omdbapi.com/?t=$title&apikey=c5409719")
        val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

        try{
            val askData: InputStream = BufferedInputStream(connection.inputStream)
            val getMovie=readStream(askData)
            val result = Gson().fromJson(getMovie, Array<Movie>::class.java)
            SingletonMovies.db.MovieDao().insert(result);
            return getMovie
        }catch (e: Exception){
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
        return ""
    }

    private fun readStream(inputStream: InputStream) : String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        while (true) {
            val line = br.readLine() ?: break
            total.append(line).append('\n')
        }
        return total.toString()
    }
}