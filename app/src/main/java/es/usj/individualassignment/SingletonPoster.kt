package es.usj.individualassignment


import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object SingletonPoster {

    fun getPoster(movie: Movie, holder: View){
        val titleProcessed = movie.title.replace(' ', '+')
        val url= URL("http://www.omdbapi.com/?t=$titleProcessed&apikey=c8151f81")
        val connectionAPI: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val askData: InputStream = BufferedInputStream(connectionAPI.inputStream)
            val data=readStream(askData)
            val result = Gson().fromJson(data, Poster::class.java)
            if(result.Image != null){
                saveImage(result.Image,holder,movie.id)
            }
        }catch (e: Exception){
            e.printStackTrace()
            Log.e("error",e.printStackTrace().toString())
        } finally {
            connectionAPI.disconnect()
        }
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

    private fun saveImage(result: String, holder: View, id:Int){
        val bitmap = Picasso.get().load(result).get()
        val imagePath = prepareData(id.toString(),holder)
        try {
            val out = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
            out.close()
        }catch (e : java.lang.Exception){
            Log.e("error", e.printStackTrace().toString())
        }
    }

    fun prepareData(id: String,holder: View): File {
        val cw = ContextWrapper(holder.context)
        val directory =  cw.getDir("images", Context.MODE_PRIVATE)
        val nameImage = id + ".jpg"
        return File(directory,nameImage)
    }

    fun showImage(movie: Movie, holder: View): File {
        val imagePath = prepareData(movie.id.toString(), holder)
        if(!imagePath.exists()){
            GlobalScope.launch {
                getPoster(movie,holder)
            }


        }
        return imagePath
    }

    fun deleteImage(id:Int, holder: View){
        val imagePath = prepareData(id.toString(),holder)
        if(imagePath.exists()){
            imagePath.delete()
        }

    }


}