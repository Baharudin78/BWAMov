package com.baharudin.bwamov.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baharudin.bwamov.R
import com.baharudin.bwamov.WalletActivity
import com.baharudin.bwamov.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragmant_setting.*

class SettingFragment : Fragment() {

    private lateinit var preference : Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmant_setting,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preferences(requireContext())

        tv_user.text = preference.getValue("nama")
        tv_email_user.text = preference.getValue("email")
        if (preference.getValue("url").equals("")){
            iv_foto_user.setImageResource(R.drawable.ic_group)
        }else{
            Glide.with(requireContext())
                .load(preference.getValue("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_foto_user)
        }
        tv_my_wallet.setOnClickListener {
            startActivity(Intent(activity,WalletActivity::class.java))
        }
    }

}