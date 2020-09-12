package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        bt_lanjutkan.setOnClickListener {
            startActivity(Intent(this,OnboardingThreeAct::class.java))
        }
        bt_lewati.setOnClickListener {
            startActivity(Intent(this,OnboardingThreeAct::class.java))
        }
    }
}