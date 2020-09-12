package com.baharudin.bwamov.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.DetailsAct
import com.baharudin.bwamov.R
import com.baharudin.bwamov.adapter.ComingSoonAdapter
import com.baharudin.bwamov.adapter.NowPlayingAdapter
import com.baharudin.bwamov.modul.Film
import com.baharudin.bwamov.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var preference : Preferences
    private lateinit var mDatabase : DatabaseReference

    private var dataList = ArrayList<Film>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        preference = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_nama.setText(preference.getValue("nama"))
        if (!preference.getValue("saldo").equals("")){
            currency(preference.getValue("saldo")!!.toDouble(),tv_saldo)
        }else if(preference.getValue("url").equals("")){
            iv_photo.setImageResource(R.drawable.ic_group)
        }else{
            Glide.with(this)
                .load(preference.getValue("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_photo)
        }

        getData()

        rv_nowPlaying.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        rv_comingSoon.layoutManager = LinearLayoutManager(context)

    }

    private fun getData() {
        mDatabase.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getDataFile in dataSnapShot.children){
                    val film = getDataFile.getValue(Film::class.java)
                    dataList.add(film!!)

                }
                rv_nowPlaying.adapter = NowPlayingAdapter(dataList){
                    val intent = Intent(context,DetailsAct::class.java).putExtra("data",it)
                    startActivity(intent)
                }
                rv_comingSoon.adapter = ComingSoonAdapter(dataList){
                    val intent = Intent(context,DetailsAct::class.java).putExtra("data",it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(dataBaseError: DatabaseError) {
                Toast.makeText(context,dataBaseError.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun currency(harga : Double , textView: TextView){
        val localID = Locale("in","ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }

}