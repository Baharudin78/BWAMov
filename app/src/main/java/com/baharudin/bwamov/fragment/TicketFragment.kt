package com.baharudin.bwamov.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.R
import com.baharudin.bwamov.TiketActivity
import com.baharudin.bwamov.adapter.ComingSoonAdapter
import com.baharudin.bwamov.modul.Film
import com.baharudin.bwamov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ticket.*

class TicketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rv_jumlah_film.layoutManager = LinearLayoutManager(context)
            getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (getDataSnap in snapshot.children){
                    val film = getDataSnap.getValue(Film::class.java)
                    dataList.add(film!!)
                }
                rv_jumlah_film.adapter = ComingSoonAdapter(dataList){
                    startActivity(Intent(context,TiketActivity::class.java).putExtra("data",it))
                }
                tv_jumlah.setText("${ dataList.size } Movie")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }

        })

    }
}