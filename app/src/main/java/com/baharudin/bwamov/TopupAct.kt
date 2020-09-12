package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_topup.*

class TopupAct : AppCompatActivity() {

    private var status : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)

        bt_topup_now.setOnClickListener {
            startActivity(Intent(this,SuccesTopupAct::class.java))
        }
        tv_10k.setOnClickListener {
            if (status){
                unSelected(tv_10k)
            }else{
                selectMOney(tv_10k)
            }
        }

    }

    private fun selectMOney(textView : TextView){

        textView.setTextColor(resources.getColor(R.color.redPrimary))
        textView.setBackgroundResource(R.drawable.shape_retangle_red)
        status = true
        bt_topup_now.visibility = View.VISIBLE
    }
    private fun unSelected(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_retangle_white)
        status = false

    }
}