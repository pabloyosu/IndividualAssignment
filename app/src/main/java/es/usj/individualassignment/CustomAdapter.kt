package es.usj.individualassignment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class CustomAdapter(var listaMovies:List<Movie>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(), Filterable{
    
    var lista:List<Movie>
    val listaFija:List<Movie>
    init {
        lista = listaMovies
        listaFija=listaMovies
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = lista.get(position).title
        holder.itemDetail.text = lista.get(position).rating.toString()
        holder.itemImage.setImageResource(R.drawable.ic_launcher_foreground)
        holder.click(lista.get(position))

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init{
            itemImage = itemView.findViewById(R.id.image)
            itemTitle = itemView.findViewById(R.id.title)
            itemDetail = itemView.findViewById(R.id.textRating)
        }

        fun click(movie : Movie){
            itemView.setOnClickListener{
               val intent = Intent (itemView.context, A5ViewMovie::class.java)
                intent.putExtra("movie", movie)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    inner class FilterMovies: Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            var listaFiltrada:ArrayList<Movie> = arrayListOf()
            listaFiltrada = listaFija.filter { p0!= null && it.title.lowercase(Locale.getDefault()).contains(
                p0.toString().lowercase(Locale.getDefault())
            ) } as ArrayList<Movie>

            var filterResult = FilterResults()
            filterResult.values=listaFiltrada

            return filterResult
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            lista = listOf()
            if (p1 != null) {
                lista= p1.values as List<Movie>
            }
            notifyDataSetChanged()
        }


    }

    override fun getFilter(): Filter {
        return FilterMovies()
    }


}