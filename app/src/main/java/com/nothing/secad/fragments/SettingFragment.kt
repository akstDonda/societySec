package com.nothing.secad.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.nothing.secad.ChangePasswordActivity
import com.nothing.secad.R
import com.nothing.secad.databinding.FragmentSettingBinding
import com.nothing.secad.setting.CustomerCare
import com.nothing.secad.simple.LoginActivity
import com.nothing.secad.simple.welcome_signUp_login


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can interact with the views in the layout here
        binding.llChangePassword.setOnClickListener(){
            var intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)

        }

        //customer care
        binding.customerCareLl.setOnClickListener(){
            var intent = Intent(context, CustomerCare::class.java)
            startActivity(intent)
        }

        //logOut
        binding.logOutBtn.setOnClickListener() {
            val firebaseAuth = FirebaseAuth.getInstance()
            //TODO:DialogBox
            firebaseAuth.signOut()
            var intent = Intent(requireActivity(), welcome_signUp_login::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up any resources here
    }
}
