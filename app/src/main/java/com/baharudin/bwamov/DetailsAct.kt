package com.baharudin.bwamov

import android.content.Intent
import android.gesture.GestureLibraries
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.adapter.PlaysAdapter
import com.baharudin.bwamov.fragment.DashboardFragment
import com.baharudin.bwamov.modul.Film
import com.baharudin.bwamov.modul.Play
import com.baharudin.bwamov.utils.Preferences
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_details.*

class DetailsAct : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var datalist = ArrayList<Play>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        tv_judul.text = data?.judul
        tv_genre.text = data?.genre
        tv_rating.text = data?.rating
        tv_storyboard.text = data?.desc
        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster)

        ic_back.setOnClickListener {
            val intent = Intent(this,DashboardFragment::class.java)
            startActivity(intent)
        }
        bt_pilih_bangku.setOnClickListener {
            val intent = Intent(this,KursiAct::class.java).putExtra("data",data)
            startActivity(intent)
        }

        getData()
        rv_whoplay.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (getDataSnapShot in snapshot.children){
                    val film = getDataSnapShot.getValue(Play::class.java)
                    datalist.add(film!!)
                }
                rv_whoplay.adapter = PlaysAdapter(datalist){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailsAct,error.message,Toast.LENGTH_SHORT).show()
            }

        })
    }
}