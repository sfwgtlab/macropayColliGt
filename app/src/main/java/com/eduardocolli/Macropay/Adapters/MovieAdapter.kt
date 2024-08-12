package com.eduardocolli.Macropay.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eduardocolli.Macropay.Models.Genre
import com.eduardocolli.Macropay.Models.Movie
import com.eduardocolli.Macropay.R
import com.squareup.picasso.Picasso
import java.io.Serializable

class MovieAdapter(val movies : List<Movie>, val context: Context): RecyclerView.Adapter<MovieAdapter.ServiceHolder>() {

    var onItemClick : ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.movie_adapter, parent, false)
        return ServiceHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        val element = movies[position]
        holder.view.setOnClickListener {
            onItemClick?.invoke(element)
        }
        holder.render(element, context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ServiceHolder(val view: View):RecyclerView.ViewHolder(view) {

        fun render(movie: Movie, context: Context ){
            val img = view.findViewById<ImageView>(R.id.IV_movie)
            val name = view.findViewById<TextView>(R.id.txt_movie_name)
            val cal = view.findViewById<TextView>(R.id.txt_movie_cal)
            name.text = movie.title
            cal.text = "%.2f".format(movie.vote_average)
            val imgUrl = context.resources.getString(R.string.img_domain)+movie.poster_path
            Picasso.get().load(imgUrl).into(img)
        }

    }


}