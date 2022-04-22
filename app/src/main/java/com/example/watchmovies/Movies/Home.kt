package com.example.watchmovies.Movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.Adapter.AdapterMovie
import com.example.watchmovies.Model.Movie
import com.example.watchmovies.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView_movies = findViewById<RecyclerView>(R.id.recylerView_movies)
        recyclerView_movies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView_movies.setHasFixedSize(true)

        val movieList: MutableList<Movie> = mutableListOf()
        val adapterMovie = AdapterMovie(this, movieList)
        recyclerView_movies.adapter = adapterMovie

        val movie_01 = Movie(
            R.drawable.sonic,
            "Sonic the Hedgehog 2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        val movie_02 = Movie(
            R.drawable.batman,
            "Batman",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        val movie_03 = Movie(
            R.drawable.alita,
            "Alita: Battle Angel",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        val movie_04 = Movie(
            R.drawable.kakegurui,
            "Kakegurui",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        val movie_05 = Movie(
            R.drawable.red,
            "Red: growing up is a beast",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        val movie_06 = Movie(
            R.drawable.the_prototype,
            "The Prototype",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec accumsan urna arcu, vitae vestibulum augue imperdiet et. Suspendisse potenti. Fusce vitae facilisis odio. Donec auctor eros eu mollis ullamcorper."
        )
        movieList.add(movie_01)
        movieList.add(movie_02)
        movieList.add(movie_03)
        movieList.add(movie_04)
        movieList.add(movie_05)
        movieList.add(movie_06)
    }
}