package com.arlanov.androidapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arlanov.androidapp.databinding.ItemRepositoryBinding
import com.arlanov.androidapp.model.models.RepositoryModelWrapper
import com.squareup.picasso.Picasso


class Adapter(val listener: (RepositoryModelWrapper) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list = listOf<RepositoryModelWrapper>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding!!.repository = list[position]
        holder.itemView.setOnClickListener {
            listener.invoke(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun update(list: List<RepositoryModelWrapper>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
        var binding: ItemRepositoryBinding? = DataBindingUtil.bind(v!!)
    }
}
@BindingAdapter("app:image_url")
fun ImageView.loadImage(url: String?){
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(this)
}