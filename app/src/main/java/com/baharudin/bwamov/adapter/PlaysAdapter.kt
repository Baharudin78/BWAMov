package com.baharudin.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.bwamov.R
import com.baharudin.bwamov.modul.Play
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlaysAdapter(private var data : List<Play>,
                   private var listener : (Play)-> Unit)
    : RecyclerView.Adapter<PlaysAdapter.MyViewHolder>() {
    lateinit var contextAdapter : Context

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        private var tvNama : TextView = view.findViewById(R.id.tv_nama)
        private var ivFoto : ImageView = view.findViewById(R.id.iv_foto_user)

        fun bindItem(data: Play,listener: (Play) -> Unit,context: Context){
            tvNama.text = data.nama
            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFoto)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflaterView = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = inflaterView.inflate(R.layout.item_who_play,parent,false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size
}