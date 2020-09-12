package com.baharudin.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.bwamov.R
import com.baharudin.bwamov.modul.Checkout

class TiketAdapter(private var data : List<Checkout>,private var listener : (Checkout)-> Unit ):RecyclerView.Adapter<TiketAdapter.MyViewHolder>() {
    lateinit var contextAdapter : Context

    class MyViewHolder(view : View):RecyclerView.ViewHolder(view){
        private var tvTitle : TextView = view.findViewById(R.id.tv_kursi)

        fun bindData(data : Checkout,listener: (Checkout) -> Unit){
            tvTitle.text = data.kursi
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = inflater.inflate(R.layout.item_checkout_kursi,parent,false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position],listener)
    }

    override fun getItemCount(): Int = data.size
}