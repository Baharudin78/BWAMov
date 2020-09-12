package com.baharudin.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.bwamov.modul.User
import com.baharudin.bwamov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterAct : AppCompatActivity() {
    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var iEmail : String
    private lateinit var iNama : String

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInsance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference
    private lateinit var mPreference : Preferences
    lateinit var data: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mFirebaseInsance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        mDatabaseReference = mFirebaseInsance.getReference("User")
        mPreference = Preferences(this)

        bt_next_save.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()
            iEmail = et_email.text.toString()
            iNama = et_nama.text.toString()

            if (iUsername.equals("")){
                et_username.error = "masukkan username anda"
                et_username.requestFocus()
            }else if (iPassword.equals("")){
                et_password.error = "masukkan password anda"
                et_password.requestFocus()
            }else if (iEmail.equals("")){
                et_email.error = "masukkan email anda"
                et_email.requestFocus()
            }else if (iNama.equals("")){
                et_nama.error =" masukkan nama anda"
                et_nama.requestFocus()
            }else{
                saveUser(iUsername,iPassword,iEmail,iNama)
            }
        }
    }

    private fun saveUser(iUsername: String, iPassword: String, iEmail: String, iNama: String) {
        val user = User()
        user.username = iUsername
        user.password = iPassword
        user.email = iEmail
        user.nama = iNama

        if (iUsername != null){
            checkUser(iUsername, user)
        }

    }

    private fun checkUser(iUsername: String, data : User) {
        mDatabaseReference.child(iUsername).addValueEventListener( object  : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@RegisterAct,databaseError.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(iUsername).setValue(data)
                    mPreference.setValue("nama",data.nama.toString())
                    mPreference.setValue("username",data.username.toString())
                    mPreference.setValue("saldo","")
                    mPreference.setValue("url","")
                    mPreference.setValue("email",data.email.toString())
                    mPreference.setValue("status","1")

                    val goRegister = Intent(this@RegisterAct,PhotoAct::class.java).putExtra("data", data)
                    startActivity(goRegister)
                }else{
                    Toast.makeText(this@RegisterAct,"username sudah digunakkan",Toast.LENGTH_LONG).show()
                }

            }
        })

    }
}