package es.usj.individualassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import es.usj.individualassignment.databinding.ActivityA4EditMovieBinding
import es.usj.individualassignment.databinding.ActivityA5ViewMovieBinding
import es.usj.individualassignment.databinding.ActivityA6AddMovieBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class A6AddMovie : AppCompatActivity() {
    private lateinit var binding: ActivityA6AddMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityA6AddMovieBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ok, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ok -> {
                GlobalScope.launch {
                    val movie = Movie(SingletonMovies.db.MovieDao().getMax()+1,
                        binding.textEditTitle.text.toString(),
                        getListExport(binding.etActor.text.toString()),
                        binding.textViewSynopsisValue.text.toString(),
                        binding.textViewDirectorValue.text.toString(),
                        getListExport(binding.etGenres.text.toString()),
                        binding.textViewReleaseDateValue.text.toString().toInt(),
                        binding.textViewRuntimeValue.text.toString().toInt(),
                        binding.textViewRatingValue.text.toString().toFloat(),
                        0,
                        0.0f
                    )
                    API.createMovie(movie)
                    SingletonMovies.db.MovieDao().insert(movie)
                    SingletonLista.list.add(movie)
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
}