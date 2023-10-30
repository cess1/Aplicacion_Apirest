package com.example.android_kt_retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AutorAdapter(private val data: List<AutorLibro>) : RecyclerView.Adapter<AutorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val autorTextView: TextView = itemView.findViewById(R.id.txtAutor)
        val libroTextView: TextView = itemView.findViewById(R.id.txtLibro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_modal_dialog, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val autorLibro = data[position]
        holder.autorTextView.text = autorLibro.autor
        holder.libroTextView.text = autorLibro.libro

    }

    override fun getItemCount() = data.size
}

