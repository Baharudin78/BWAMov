package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.baharudin.bwamov.utils.Preferences

class SplashActivity : AppCompatActivity() {
    private lateinit var preference : Preferences
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        getUser()
    }
    private var runnable = Runnable {
        startActivity(Intent(applicationContext,OnboardingAct::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
//    private fun getUser(){
//        preference = Preferences(this)
//        preference.getValue("USER_PREF")
//
//        if (preference.equals("")){
//            runnable
//        }else{
//            handler.postDelayed({
//                startActivity(Intent(this,HomeAct::class.java))
//            },3000)
//
//        }
//
//    }
}