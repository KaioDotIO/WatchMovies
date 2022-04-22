package com.example.watchmovies.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.Model.Movie
import com.example.watchmovies.R

class AdapterMovie(private val context: Context, private val movies:MutableList<Movie>): RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        val holder = MovieViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.thumb.setImageResource(movies[position].thumb)
        holder.title.text = movies[position].title
        holder.description.text = movies[position].description
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumb = itemView.findViewById<ImageView>(R.id.movie_thumb)
        val title = itemView.findViewById<TextView>(R.id.movie_title)
        val description = itemView.findViewById<TextView>(R.id.movie_description)
    }
}