package es.usj.individualassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.individualassignment.databinding.ActivityA7ActorsViewBinding
import es.usj.individualassignment.databinding.ActivityA7GenresViewBinding

class A7GenresView : AppCompatActivity() {

    private lateinit var binding: ActivityA7GenresViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityA7GenresViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val genre = intent.getSerializableExtra("genre") as Genre

        binding.textViewGenre.text = genre.name
        var listaGenres:ArrayList<Movie> = ArrayList()

        for(genero in SingletonLista.list){
            if(genero.genres.contains(genero.id)){
                listaGenres.add(genero)
            }
        }

        binding.rvGenre.layoutManager = LinearLayoutManager(this)
        binding.rvGenre.adapter= CustomAdapter(listaGenres)
    }
}