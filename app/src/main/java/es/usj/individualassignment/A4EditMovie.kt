package es.usj.individualassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.room.util.StringUtil
import es.usj.individualassignment.databinding.ActivityA4EditMovieBinding
import es.usj.individualassignment.databinding.ActivityA5ViewMovieBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class A4EditMovie : AppCompatActivity() {
    private lateinit var binding: ActivityA4EditMovieBinding
    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityA4EditMovieBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        movie = intent.getSerializableExtra("movie") as Movie

        binding.textEditTitle.append(movie.title)
        binding.textViewRatingValue.append(movie.rating.toString())
        binding.textViewReleaseDateValue.append(movie.year.toString())
        binding.textViewSynopsisValue.append(movie.description)
        binding.textViewDirectorValue.append(movie.director)
        binding.textViewRuntimeValue.append(movie.runtime.toString())
        binding.etActor.append(getListImport(movie.actors))
        binding.etGenres.append(getListImport(movie.genres))



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ok, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ok -> {
                GlobalScope.launch {
                    val movieNew = Movie(movie.id,
                        binding.textEditTitle.text.toString(),
                        getListExport(binding.etActor.text.toString()),
                        binding.textViewSynopsisValue.text.toString(),
                        binding.textViewDirectorValue.text.toString(),
                        getListExport(binding.etGenres.text.toString()),
                        binding.textViewReleaseDateValue.text.toString().toInt(),
                        binding.textViewRuntimeValue.text.toString().toInt(),
                        binding.textViewRatingValue.text.toString().toFloat(),
                        movie.votes,
                        movie.revenue
                    )
                    API.updateMovie(movieNew)
                    SingletonMovies.db.MovieDao().update(movieNew)
                    SingletonLista.list.add(SingletonLista.list.indexOf(movie), movieNew)
                    SingletonLista.list.remove(movie)
                    if(movie.title != movieNew.title){
                        SingletonPoster.deleteImage(movie.id,binding.etActor)
                        SingletonPoster.showImage(movieNew,binding.etActor)
                    }

                    val intent = Intent()
                    intent.putExtra("movie", movieNew)
                    setResult(Activity.RESULT_OK, intent)

                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getListExport(string : String):List <Int>{
        val list : ArrayList<Int> = arrayListOf()
        val listS : ArrayList<String> = arrayListOf()
        listS.addAll(string.split(","))
        for (num in listS){
            list.add(num.toInt())
        }

        return list
    }

    fun getListImport(lista : List<Int>): String{
        var string = ""
        for(num in lista){
            string = string + num.toString() + ","
        }
        string = string.removeSuffix(",")

        return string
    }


}