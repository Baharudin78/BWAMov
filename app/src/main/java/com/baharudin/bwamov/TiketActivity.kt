package com.baharudin.bwamov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.adapter.TiketAdapter
import com.baharudin.bwamov.modul.Checkout
import com.baharudin.bwamov.modul.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {
    private var dataList= ArrayList<Checkout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Film>("data")
        tv_judul.setText(data?.judul)
        tv_genre.setText(data?.genre)
        tv_rating.setText(data?.rating)
        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster)

        rv_jumlah_tiket.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("A1","70000"))
        dataList.add(Checkout("A2","70000"))
        rv_jumlah_tiket.adapter = TiketAdapter(dataList){}
    }
}