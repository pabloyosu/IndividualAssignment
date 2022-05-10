package es.usj.individualassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.usj.individualassignment.databinding.ActivityA2MovieListBinding

class A2MovieList : AppCompatActivity() {

    private lateinit var binding: ActivityA2MovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityA2MovieListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter= CustomAdapter(SingletonLista.list)
    }
}