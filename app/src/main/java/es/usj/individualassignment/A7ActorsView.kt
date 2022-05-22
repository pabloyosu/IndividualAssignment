package es.usj.individualassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.individualassignment.databinding.ActivityA5ViewMovieBinding
import es.usj.individualassignment.databinding.ActivityA7ActorsViewBinding

class A7ActorsView : AppCompatActivity() {

    private lateinit var binding: ActivityA7ActorsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityA7ActorsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actor = intent.getSerializableExtra("actor") as Actor

        binding.textView2.text = actor.name
        var listaMovies:ArrayList<Movie> = ArrayList()

        for(pelicula in SingletonLista.list){
            if(pelicula.actors.contains(actor.id)){
                listaMovies.add(pelicula)
            }
        }

        binding.rvActor.layoutManager = LinearLayoutManager(this)
        binding.rvActor.adapter= CustomAdapter(listaMovies)
    }
}