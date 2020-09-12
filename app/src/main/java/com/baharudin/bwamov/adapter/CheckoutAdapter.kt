package com.baharudin.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.bwamov.R
import com.baharudin.bwamov.modul.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data : List<Checkout>,private var listener : (Checkout) -> Unit) : RecyclerView.Adapter<CheckoutAdapter.MyViewHolder>() {

    lateinit var contexAdapter : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val inflaterVIew = LayoutInflater.from(parent.context)
        contexAdapter = parent.context
        val inflated = inflaterVIew.inflate(R.layout.item_checkout,parent,false)
        return MyViewHolder(inflated)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contexAdapter)
    }

    override fun getItemCount(): Int = data.size

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private var tvKursi : TextView = view.findViewById(R.id.tv_kursi)
        private var tvHarga : TextView = view.findViewById(R.id.tv_harga)

        fun bindItem(data :Checkout, listener: (Checkout) -> Unit, context: Context){
            tvKursi.text = data.kursi

            //membuat function untuk memberikan format mata uang
            val localID = Locale("id","ID")
            val harga = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(harga.format(data.harga!!.toDouble()))
            //untuk menhilangkan ikon kursi dan menganti dengan kata total d awal kalimat
            if (data.kursi!!.startsWith("Total")){
                tvKursi.setText(data.kursi)
                tvKursi.setCompoundDrawables(null,null,null,null)
            }else{
                tvKursi.setText("Seat no. "+ data.kursi)
            }
            itemView.setOnClickListener {
                listener(data)
            }

        }
    }
}