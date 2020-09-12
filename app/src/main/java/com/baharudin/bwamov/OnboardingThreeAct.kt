package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        bt_mulai.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}