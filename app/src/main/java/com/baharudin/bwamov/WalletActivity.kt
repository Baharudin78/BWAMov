package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.bwamov.adapter.WalletAdapter
import com.baharudin.bwamov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity() {

    private  var dataList = ArrayList<Wallet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        dataList.add(
            Wallet(
                "Avenger Return",
                "Sabtu,10 Agustus 2020",
                70000.0,
                "0"

        )
        )
        dataList.add(
            Wallet(
                "Top up",
                "Sabtu,10 Agustus 2020",
                170000.0,
                "1"

            )
        )
        dataList.add(
            Wallet(
                "Avenger Return",
                "Sabtu,10 Agustus 2020",
                70000.0,
                "0"

            )
        )
        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList)

        bt_topup.setOnClickListener {
            startActivity(Intent(this,TopupAct::class.java))
        }

    }
}