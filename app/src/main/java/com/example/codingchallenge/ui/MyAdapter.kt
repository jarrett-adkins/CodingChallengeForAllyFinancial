package com.example.codingchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.R
import com.example.codingchallenge.model.State
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter (context: Context, val clickListener: (State) -> Unit): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var states: List<State> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val name = view.tv_state

        fun bind(state: State, clickListener: (State) -> Unit) {
            name.text = state.name

//            name.setOnClickListener {
//                val dialog: AlertDialog = AlertDialog.Builder(context).setMessage("message")
//                    .setPositiveButton("okay") { dialog, which ->
//                        dialog.dismiss()
//                    }.setNegativeButton("cancel") { dialog, which ->
//                            dialog.dismiss()
//                    }.setOnCancelListener { dialog ->
//                        dialog.dismiss()
//                    }.create()
//                dialog.show()
//            }

            name.setOnClickListener {clickListener(state)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = states.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(states[position], clickListener)
    }
}