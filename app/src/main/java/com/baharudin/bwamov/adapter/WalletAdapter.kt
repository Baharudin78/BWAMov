package com.baharudin.bwamov.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.bwamov.R
import com.baharudin.bwamov.wallet.model.Wallet
import java.text.NumberFormat
import java.util.*

class WalletAdapter(private var data : List<Wallet>):RecyclerView.Adapter<WalletAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflated = inflater.inflate(R.layout.item_row_wallet,parent,false)
        return MyViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        private var tvTitle : TextView = itemView.findViewById(R.id.tv_title)
        private var tvDate : TextView = itemView.findViewById(R.id.tv_date)
        private var tvUang : TextView = itemView.findViewById(R.id.tv_uang)

        fun bindData(data : (Wallet)){
            tvTitle.text = data.title
            tvDate.text = data.date
            tvUang.text = data.uang.toString()

            val localID = Locale("in","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)

            if (data.status.equals("0")){
                tvUang.text = "= " + formatRupiah.format(data.uang)
            }else{
                tvUang.text = "+ " + formatRupiah.format(data.uang)
                tvUang.setTextColor(Color.GREEN)
            }
        }

    }
}