package es.usj.individualassignment

import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class API {
    fun getMovies():String{
        val urlConnection = URL("http://10.0.2.2:8080/movies")
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

    fun getActors():String{
        val urlConnection = URL("http://10.0.2.2:8080/actors")
        val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

        try{
            val askData: InputStream = BufferedInputStream(connection.inputStream)
            val getActors=readStream(askData)
            val result = Gson().fromJson(getActors, Array<Actor>::class.java)
            SingletonMovies.db.ActorDao().insert(result);
            return getActors
        }catch (e: Exception){
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
        return ""
    }

    fun getGenre():String{
        val urlConnection = URL("http://10.0.2.2:8080/genres")
        val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

        try{
            val askData: InputStream = BufferedInputStream(connection.inputStream)
            val getGenre=readStream(askData)
            val result = Gson().fromJson(getGenre, Array<Genre>::class.java)
            SingletonMovies.db.GenreDao().insert(result);
            return getGenre
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