package es.usj.individualassignment

import com.google.gson.Gson
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class API {
    companion object {

        fun getMovies(): String {
            val urlConnection = URL("http://10.0.2.2:8080/movies")
            val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

            try {
                val askData: InputStream = BufferedInputStream(connection.inputStream)
                val getMovie = readStream(askData)
                val result = Gson().fromJson(getMovie, Array<Movie>::class.java)
                SingletonMovies.db.MovieDao().insert(result);
                return getMovie
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
            return ""
        }

        fun getActors(): String {
            val urlConnection = URL("http://10.0.2.2:8080/actors")
            val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

            try {
                val askData: InputStream = BufferedInputStream(connection.inputStream)
                val getActors = readStream(askData)
                val result = Gson().fromJson(getActors, Array<Actor>::class.java)
                SingletonMovies.db.ActorDao().insert(result);
                return getActors
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
            return ""
        }

        fun getGenre(): String {
            val urlConnection = URL("http://10.0.2.2:8080/genres")
            val connection: HttpURLConnection = urlConnection.openConnection() as HttpURLConnection

            try {
                val askData: InputStream = BufferedInputStream(connection.inputStream)
                val getGenre = readStream(askData)
                val result = Gson().fromJson(getGenre, Array<Genre>::class.java)
                SingletonMovies.db.GenreDao().insert(result);
                return getGenre
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
            return ""
        }

        fun updateMovie(movie: Movie) {
            try {
                val url = URL("http://10.0.2.2:8080/movies")
                val httpCon = url.openConnection() as HttpURLConnection
                httpCon.addRequestProperty("Content-Type", "application/json; utf-8")
                httpCon.addRequestProperty("Accept", "application/json")
                httpCon.doOutput = true
                httpCon.requestMethod = "PUT"
                val out = OutputStreamWriter(
                    httpCon.outputStream
                )
                val jsonString = Gson().toJson(movie, Movie::class.java)
                out.write(jsonString)
                out.flush()
                out.close()
                println(httpCon.getResponseCode());
            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }

        fun createMovie(movie: Movie) {
            try {
                val url = URL("http://10.0.2.2:8080/movies")
                val httpCon = url.openConnection() as HttpURLConnection
                httpCon.addRequestProperty("Content-Type", "application/json; utf-8")
                httpCon.addRequestProperty("Accept", "application/json")
                httpCon.doOutput = true
                httpCon.requestMethod = "POST"
                val out = OutputStreamWriter(
                    httpCon.outputStream
                )
                val jsonString = Gson().toJson(movie, Movie::class.java)
                out.write(jsonString)
                out.flush()
                out.close()
                println(httpCon.getResponseCode());
            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }

        private fun readStream(inputStream: InputStream): String {
            val br = BufferedReader(InputStreamReader(inputStream))
            val total = StringBuilder()
            while (true) {
                val line = br.readLine() ?: break
                total.append(line).append('\n')
            }
            return total.toString()
        }

    }
}