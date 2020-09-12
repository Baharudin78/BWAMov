package com.baharudin.bwamov

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baharudin.bwamov.modul.User
import com.baharudin.bwamov.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class PhotoAct : AppCompatActivity() ,PermissionListener{

    private val REQUEST_CODE_IMAGE = 1
    private var statusAdd : Boolean = false
    private lateinit var filePath : Uri
    private lateinit var mStorage : FirebaseStorage
    private lateinit var mStorageRef : StorageReference
    private lateinit var mPreferences: Preferences
    private lateinit var mDatabaseReference : DatabaseReference
    private lateinit var mFirebaseInsance : FirebaseDatabase
    lateinit var user : User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        mPreferences = Preferences(this)
        mStorage = FirebaseStorage.getInstance()
        mStorageRef = mStorage.getReference()
        mFirebaseInsance =  FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseInsance.getReference("User")


        tv_hello.text = "Selamat Datang\n" + intent.getStringExtra("nama")

        iv_add.setOnClickListener {
            if (statusAdd){
                statusAdd = false
                bt_save_next.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.ic_baseline_add_circle_24)
                ivPhoto.setImageResource(R.drawable.ic_group)
            }else {

                ImagePicker.with(this)
                    .galleryOnly()
                    .start()

            }
        }
        bt_lewati.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this,HomeAct::class.java))
        }
        bt_save_next.setOnClickListener {
            if (filePath != null){
                val progressBar = ProgressDialog(this)
                progressBar.setTitle("Uploading...")
                progressBar.show()

                val ref = mStorageRef.child("images/"+UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressBar.dismiss()
                            Toast.makeText(this,"Uploaded",Toast.LENGTH_LONG).show()

                            ref.downloadUrl.addOnSuccessListener {
                                mPreferences.setValue("url", it.toString())
                                saveToFirebase(it.toString())
                            }
                        }
                        .addOnFailureListener{
                            progressBar.dismiss()
                            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
                        }
                        .addOnProgressListener {
                            taskSnapshot ->  val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                            progressBar.setMessage("Upload"+ progress.toInt()+"%")
                        }
            }
        }

    }

    private fun saveToFirebase(url: String) {
        mDatabaseReference.child(user.username!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user.url = url
                mDatabaseReference.child(user.username!!).setValue(user)

                mPreferences.setValue("nama", user.nama.toString())
                mPreferences.setValue("username", user.username.toString())
                mPreferences.setValue("saldo", "")
                mPreferences.setValue("url", "")
                mPreferences.setValue("email", user.email.toString())
                mPreferences.setValue("status", "1")
                mPreferences.setValue("url", url)

                finishAffinity()
                val intent = Intent(this@PhotoAct, HomeAct::class.java)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PhotoAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent,REQUEST_CODE_IMAGE)
            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,"permission digagalkan",Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this,"anda menekan tombol kembali",Toast.LENGTH_LONG).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            filePath = data?.data!!
            statusAdd = true

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPhoto)

            Log.i("foto","foto berhasil")
            bt_save_next.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_baseline_delete_24)
        }else if(requestCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "telah dibatalkan", Toast.LENGTH_SHORT).show()
        }
    }
}