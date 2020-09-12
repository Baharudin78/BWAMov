package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_succes_topup.*

class SuccesTopupAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_topup)

        bt_back_home.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this,HomeAct::class.java))
        }
    }
}