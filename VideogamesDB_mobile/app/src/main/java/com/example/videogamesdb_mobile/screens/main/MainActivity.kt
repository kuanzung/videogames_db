package com.example.videogamesdb_mobile.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.videogamesdb_mobile.App
import com.example.videogamesdb_mobile.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        GameViewModelFactory((application as App).repository)
    }

    var adapter: GameListAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        val adapter = GameListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mainViewModel.allGamesDao.observe(owner = this@MainActivity) { games ->
            // Update the cached copy of the games in the adapter.
            games.let { adapter.submitList(it) }
        }

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            CoroutineScope(IO).launch {
                mainViewModel.getGameList()
            }
            adapter.notifyDataSetChanged()
            refreshLayout.isRefreshing = false
        }

        mainViewModel.allGames.observe(owner = this@MainActivity) { games ->
            // Update the cached copy of the games in the adapter.
            games.let { adapter.submitList(it) }
            adapter.notifyDataSetChanged()
        }

        CoroutineScope(IO).launch {
            //getGameList()
            mainViewModel.getGameList()
        }
    }
}