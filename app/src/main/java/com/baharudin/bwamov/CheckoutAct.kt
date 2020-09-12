package com.baharudin.bwamov

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.adapter.CheckoutAdapter
import com.baharudin.bwamov.modul.Checkout
import com.baharudin.bwamov.modul.Film
import com.baharudin.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutAct : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private lateinit var preference : Preferences
    private var total : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preference = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }
        dataList.add(Checkout("Total yang harus dibayar",total.toString()))

        rv_kursi.layoutManager = LinearLayoutManager(this)
        rv_kursi.adapter = CheckoutAdapter(dataList){

        }
        bt_bayar.setOnClickListener {
            startActivity(Intent(this,SucessAct::class.java))
            showNotif(data!!)
        }

    }

    private fun showNotif(datas : Film) {
        val NOTIFICATION_CHANNEL_ID = "channel_bwa_notif"
        val context = this.applicationContext
        var notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channel_name = "notif_bwa"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID,channel_name,importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val intent = Intent(this,TiketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data",datas)
        intent.putExtras(bundle)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.mipmap.ic_launcher
                )
            )
            .setTicker("notif bwa starring")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
            .setLights(Color.RED,3000,3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses terbeli")
            .setContentText("TIket " + datas.judul+ " berhasil kamu beli. Enjoy Filmnya")
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115,builder.build())

    }
}