package es.usj.individualassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class A1Spash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        setTheme(R.style.Theme_IndividualAssignment)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getMoviesDb= API()
        val context = this

        SingletonMovies.start(this)
        GlobalScope.launch {
            getMoviesDb.getMovies()
            getMoviesDb.getActors()
            getMoviesDb.getGenre()
            SingletonLista.aniadirPeliculas()
            val sendIntent = Intent(context, A2MovieList::class.java)
            context.startActivity(sendIntent)
            context.finish()


        }
    }
}