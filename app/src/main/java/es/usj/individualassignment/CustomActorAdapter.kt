package es.usj.individualassignment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomActorAdapter (var listaActores:List<Actor>): RecyclerView.Adapter<CustomActorAdapter.ViewHolder>() {

    var lista:List<Actor> = listOf()

    init {
        lista = listaActores
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_actors_generos,
            parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = lista.get(position).name

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        init{
            itemTitle = itemView.findViewById(R.id.title_card)
        }

        /*fun click(actor : Actor){
            itemView.setOnClickListener{
                val intent = Intent (itemView.context, A5ViewMovie::class.java)
                intent.putExtra("Movie", movie)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }*/
    }


}