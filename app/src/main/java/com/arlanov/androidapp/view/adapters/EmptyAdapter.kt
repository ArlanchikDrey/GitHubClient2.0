package com.arlanov.androidapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlanov.androidapp.R

class EmptyAdapter : RecyclerView.Adapter<EmptyAdapter.ViewHolder>() {

    override fun getItemCount() = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.empty_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){}

}
