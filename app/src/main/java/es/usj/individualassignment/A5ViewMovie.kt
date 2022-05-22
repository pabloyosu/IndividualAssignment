package es.usj.individualassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.individualassignment.databinding.ActivityA2MovieListBinding
import es.usj.individualassignment.databinding.ActivityA5ViewMovieBinding

class A5ViewMovie : AppCompatActivity() {

    private lateinit var binding: ActivityA5ViewMovieBinding

    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityA5ViewMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        movie = intent.getSerializableExtra("movie") as Movie

        binding.textViewTitle.text = movie.title
        binding.textViewRatingValue.text= movie.rating.toString()
        binding.textViewReleaseDateValue.text = movie.year.toString()
        binding.textViewSynopsisValue.text = movie.description
        binding.textViewDirectorValue.text = movie.director
        binding.textViewRuntimeValue.text = movie.runtime.toString()
        binding.rvActor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvActor.adapter= CustomActorAdapter(obtenerActores(movie.actors as ArrayList<Int>))
        binding.rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGenres.adapter= CustomGenreAdapter(obtenerGenres(movie.genres as ArrayList<Int>))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.modify -> {
                val intent = Intent(this, A4EditMovie::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun obtenerActores(actores:ArrayList<Int>):ArrayList<Actor>{
        var listaActores :ArrayList<Actor> = arrayListOf()
        for (actor in actores){
            for(actor2 in SingletonLista.listActors){
                if(actor== actor2.id)
                {
                    listaActores.add(actor2)
                }
            }
        }
        return listaActores
    }

    fun obtenerGenres(genres:ArrayList<Int>):ArrayList<Genre>{
        var listaGenres :ArrayList<Genre> = arrayListOf()
        for (genre in genres){
            for(genre2 in SingletonLista.listGenres){
                if(genre== genre2.id)
                {
                    listaGenres.add(genre2)
                }
            }
        }
        return listaGenres
    }
}