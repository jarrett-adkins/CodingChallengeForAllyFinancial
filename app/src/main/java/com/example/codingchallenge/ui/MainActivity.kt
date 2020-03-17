package com.example.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingchallenge.R
import com.example.codingchallenge.model.State
import com.example.codingchallenge.networking.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyAdapter(this) { state: State -> onClick(state)}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getVotes()
    }

    fun onClick(state: State) {
        val dialog: AlertDialog = AlertDialog.Builder(this).setMessage(state.popular.democrat)
            .setPositiveButton("okay") { dialog, which ->
                dialog.dismiss()
            }.setOnCancelListener { dialog ->
                dialog.dismiss()
            }.create()
        dialog.show()
    }

    fun getVotes() {
        val retrofit = RetrofitBuilder().getRetrofit(
            "https://raw.githubusercontent.com/jarrett-adkins/presidential-election-data/master/")

        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getVotes()
            withContext(Dispatchers.Main) {
                adapter?.states = response.votes
            }
        }
    }
}
