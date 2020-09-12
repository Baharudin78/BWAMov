package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingAct : AppCompatActivity() {

    private lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {

        preference = Preferences(this)
        //membuat fungtion agar layout onoarding tidak muncul terus ketika activity dead
        if (preference.getValue("onboarding").equals("1")){
            startActivity(Intent(this,LoginActivity::class.java))
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        bt_lanjutkan.setOnClickListener {
            startActivity(Intent(this,OnboardingTwoAct::class.java))
        }
        bt_lewati.setOnClickListener {
            preference.setValue("onboarding","1")
            finishAffinity()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}