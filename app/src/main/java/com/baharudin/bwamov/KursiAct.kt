package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baharudin.bwamov.modul.Checkout
import com.baharudin.bwamov.modul.Film
import kotlinx.android.synthetic.main.activity_kursi.*

class KursiAct : AppCompatActivity() {

    var statusA1 : Boolean = false
    var statusA2 : Boolean = false
    var statusA3 : Boolean = false
    var statusA4 : Boolean = false
    var statusB1 : Boolean = false
    var statusB2 : Boolean = false
    var statusB3 : Boolean = false
    var statusB4 : Boolean = false
    var statusC1 : Boolean = false
    var statusC2 : Boolean = false
    var statusC3 : Boolean = false
    var statusC4 : Boolean = false
    var statusD1 : Boolean = false
    var statusD2 : Boolean = false
    var statusD3 : Boolean = false
    var statusD4 : Boolean = false
    var total : Int = 0

    private var dataList = ArrayList<Checkout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kursi)

        val data = intent.getParcelableExtra<Film>("data")
        tv_judul_film.text = data?.judul

        a1.setOnClickListener {
            if (statusA1){
                a1.setImageResource(R.drawable.ic_bottom)
                statusA1 = false
                total -= 1
                beliTiket(total)
            }else{
                a1.setImageResource(R.drawable.ic_bottomred)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A1","70000")
                dataList.add(data)
            }
        }
        a2.setOnClickListener {
            if (statusA2){
                a2.setImageResource(R.drawable.ic_bottom)
                statusA2 = false
                total -= 1
                beliTiket(total)
            }else{
                a2.setImageResource(R.drawable.ic_bottomred)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A2","70000")
                dataList.add(data)
            }
        }
        a3.setOnClickListener {
            if (statusA3){
                a3.setImageResource(R.drawable.ic_bottom)
                statusA1 = false
                total -= 1
                beliTiket(total)
            }else{
                a3.setImageResource(R.drawable.ic_bottomred)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3","70000")
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            if (statusA4){
                a4.setImageResource(R.drawable.ic_bottom)
                statusA4 = false
                total -= 1
                beliTiket(total)
            }else{
                a4.setImageResource(R.drawable.ic_bottomred)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4","70000")
                dataList.add(data)
            }
        }
        b1.setOnClickListener {
            if (statusB1){
                a1.setImageResource(R.drawable.ic_bottom)
                statusB1 = false
                total -= 1
                beliTiket(total)
            }else{
                b1.setImageResource(R.drawable.ic_bottomred)
                statusB1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B1","80000")
                dataList.add(data)
            }
        }
        b2.setOnClickListener {
            if (statusB2){
                b2.setImageResource(R.drawable.ic_bottom)
                statusB2 = false
                total -= 1
                beliTiket(total)
            }else{
                b2.setImageResource(R.drawable.ic_bottomred)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B2","80000")
                dataList.add(data)
            }
        }
        b3.setOnClickListener {
            if (statusB3){
                b3.setImageResource(R.drawable.ic_bottom)
                statusB3 = false
                total -= 1
                beliTiket(total)
            }else{
                b3.setImageResource(R.drawable.ic_bottomred)
                statusB3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B3","80000")
                dataList.add(data)
            }
        }
        b4.setOnClickListener {
            if (statusB4){
                b4.setImageResource(R.drawable.ic_bottom)
                statusB4 = false
                total -= 1
                beliTiket(total)
            }else{
                b4.setImageResource(R.drawable.ic_bottomred)
                statusB4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B4","80000")
                dataList.add(data)
            }
        }
        c1.setOnClickListener {
            if (statusC1){
                c1.setImageResource(R.drawable.ic_bottom)
                statusC1 = false
                total -= 1
                beliTiket(total)
            }else{
                c1.setImageResource(R.drawable.ic_bottomred)
                statusC1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C1","90000")
                dataList.add(data)
            }
        }
        c2.setOnClickListener {
            if (statusC2){
                c2.setImageResource(R.drawable.ic_bottom)
                statusC2 = false
                total -= 1
                beliTiket(total)
            }else{
                c2.setImageResource(R.drawable.ic_bottomred)
                statusC2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C2","90000")
                dataList.add(data)
            }
        }
        c3.setOnClickListener {
            if (statusC3){
                c3.setImageResource(R.drawable.ic_bottom)
                statusA1 = false
                total -= 1
                beliTiket(total)
            }else{
                c3.setImageResource(R.drawable.ic_bottomred)
                statusC3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C3","90000")
                dataList.add(data)
            }
        }
        c4.setOnClickListener {
            if (statusC4){
                c4.setImageResource(R.drawable.ic_bottom)
                statusC4 = false
                total -= 1
                beliTiket(total)
            }else{
                c4.setImageResource(R.drawable.ic_bottomred)
                statusC4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C4","90000")
                dataList.add(data)
            }
        }
        d1.setOnClickListener {
            if (statusD1){
                d1.setImageResource(R.drawable.ic_bottom)
                statusD1 = false
                total -= 1
                beliTiket(total)
            }else{
                d1.setImageResource(R.drawable.ic_bottomred)
                statusD1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D1","100000")
                dataList.add(data)
            }
        }
        d2.setOnClickListener {
            if (statusD2){
                d1.setImageResource(R.drawable.ic_bottom)
                statusD1 = false
                total -= 1
                beliTiket(total)
            }else{
                d2.setImageResource(R.drawable.ic_bottomred)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D2","100000")
                dataList.add(data)
            }
        }
            d3.setOnClickListener {
            if (statusD3){
                d3.setImageResource(R.drawable.ic_bottom)
                statusD3 = false
                total -= 1
                beliTiket(total)
            }else{
                d3.setImageResource(R.drawable.ic_bottomred)
                statusD3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D3","100000")
                dataList.add(data)
            }
        }
        d4.setOnClickListener {
            if (statusD4){
                a1.setImageResource(R.drawable.ic_bottom)
                statusD4 = false
                total -= 1
                beliTiket(total)
            }else{
                d4.setImageResource(R.drawable.ic_bottomred)
                statusD4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D4","100000")
                dataList.add(data)
            }
        }
        bt_beli_tiket.setOnClickListener {
            val intent = Intent(this,CheckoutAct::class.java).putExtra("data",dataList).putExtra("datas",data)
            startActivity(intent)
        }
    }
    private fun beliTiket(total : Int){
        if (total== 0){
            bt_beli_tiket.setText("Beli Tiket")
            bt_beli_tiket.visibility = View.INVISIBLE
        }else{
            bt_beli_tiket.setText("Beli Tiket (" +total+ ")")
            bt_beli_tiket.visibility = View.VISIBLE
        }
    }
}