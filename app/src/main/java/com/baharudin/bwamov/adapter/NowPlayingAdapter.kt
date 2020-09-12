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

class NowPlayingAdapter(private var data : List<Film>
                        ,private var listener : (Film)-> Unit ) : RecyclerView.Adapter<NowPlayingAdapter.MyViewHolder> (){

    private lateinit var contexAdapter : Context

    class MyViewHolder(view:View) : RecyclerView.ViewHolder(view){

        private var tvTitle : TextView = view.findViewById(R.id.tv_judul)
        private var tvGenre : TextView = view.findViewById(R.id.tv_genre)
        private var tvRate : TextView = view.findViewById(R.id.tv_rating)
        private  var ivPoster : ImageView = view.findViewById(R.id.iv_poster)

        fun bindItem(data: Film, listener: (Film) -> Unit, context: Context){
            tvTitle.text = data.judul
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
        contexAdapter = parent.context
        val inflated = inflaterView.inflate(R.layout.item_now_playing,parent,false)
        return MyViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contexAdapter)
    }

    override fun getItemCount(): Int = data.size
}