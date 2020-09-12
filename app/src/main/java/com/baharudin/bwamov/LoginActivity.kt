package com.baharudin.bwamov

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.bwamov.modul.User
import com.baharudin.bwamov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private  lateinit var mDatabase : DatabaseReference
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        preferences.setValue("onbarding","1")
        if (preferences.getValue("status").equals("1")){
            finishAffinity()
            startActivity(Intent(this,HomeAct::class.java))
        }
        //funtion buat tombol daftar
        tvDaftar.setOnClickListener {
            startActivity(Intent(this,RegisterAct::class.java))
        }

        btn_login.setOnClickListener {

            iUsername = etUsername.text.toString()
            iPassword = etPassword.text.toString()

            if (iUsername == ""){
                etUsername.error = "silahkan isi username anda"
                etUsername.requestFocus()
            }else if (iPassword.equals("")){
                etPassword.error = "silahkan isi password anda"
                etPassword.requestFocus()
            }else{
                pushLogin(iUsername, iPassword)
            }

        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@LoginActivity,databaseError.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)

                if (user == null){
                    Toast.makeText(this@LoginActivity,"username tidak ada",Toast.LENGTH_LONG).show()
                }else{
                    //logika untuk membuat databse sementara dengan shared reference
                    if (user.password.equals(iPassword)){
                        preferences.setValue("nama", user.nama.toString())
                        preferences.setValue("username", user.username.toString())
                        preferences.setValue("email", user.email.toString())
                        preferences.setValue("password", user.password.toString())
                        preferences.setValue("url", user.url.toString())
                        preferences.setValue("saldo", user.saldo.toString())
                        finishAffinity()
                       startActivity(Intent(this@LoginActivity,HomeAct::class.java))
                    }else{
                        Toast.makeText(this@LoginActivity,"password anda saalah",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}