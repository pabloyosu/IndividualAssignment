package es.usj.individualassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import es.usj.individualassignment.databinding.ActivityA4EditMovieBinding
import es.usj.individualassignment.databinding.ActivityA5ViewMovieBinding

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



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ok, menu)
        return super.onCreateOptionsMenu(menu)
    }


}