package es.usj.individualassignment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class CustomAdapter(var listaMovies:List<Movie>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    //val titles = arrayOf("Movie 1", "Movie 2","Movie 3", "Movie 4" )
    //val details = arrayOf("detalle 1", "detalle 2","detalle 3", "detalle 4" )
    //val images = intArrayOf(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
        //R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground)
    val lista:List<Movie>
    init {
        lista = listaMovies
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


}