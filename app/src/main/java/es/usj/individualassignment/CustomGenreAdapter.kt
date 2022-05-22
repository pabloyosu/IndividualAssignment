package es.usj.individualassignment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomGenreAdapter (var listaGenres:List<Genre>): RecyclerView.Adapter<CustomGenreAdapter.ViewHolder>() {

    var lista:List<Genre> = listOf()

    init {
        lista = listaGenres
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_actors_generos,
            parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = lista.get(position).name
        holder.click(lista.get(position))

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        init{
            itemTitle = itemView.findViewById(R.id.title_card_actor)
        }

        fun click(genre : Genre){
            itemView.setOnClickListener{
                val intent = Intent (itemView.context, A7GenresView::class.java)
                intent.putExtra("genre", genre)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }


}