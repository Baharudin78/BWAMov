package com.baharudin.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.bwamov.R
import com.baharudin.bwamov.modul.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_coming_soon.view.*

class ComingSoonAdapter (private var data : List<Film>, private  var listener : (Film) -> Unit) : RecyclerView.Adapter<ComingSoonAdapter.MyViewHolder>() {

    private lateinit var contextAdapter : Context

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view){
        private var tvJudul : TextView = view.findViewById(R.id.tv_judul_two)
        private var tvGenre : TextView = view.findViewById(R.id.tv_genre_two)
        private var tvRate : TextView = view.findViewById(R.id.tv_rating_two)
        private var ivPoster : ImageView = view.findViewById(R.id.iv_poster_two)

        fun bindItem(data : Film, listener: (Film) -> Unit, context: Context){
            tvJudul.text = data.judul
            tvGenre.text = data.genre
            tvRate.text = data.rating
            Glide.with(context)
                .load(data.poster)
                .into(ivPoster)

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflaterView = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = inflaterView.inflate(R.layout.item_coming_soon,parent,false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size
}