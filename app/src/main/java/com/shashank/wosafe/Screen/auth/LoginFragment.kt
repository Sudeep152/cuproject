package com.shashank.wosafe.Screen.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.shashank.wosafe.MapsActivity
import com.shashank.wosafe.R
import com.shashank.wosafe.Screen.DashActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    var mAuth= FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loginBtn.setOnClickListener {

            val email = LemailEdt.text.toString()
            val pass = LpassEdt.text.toString()
            if(
                TextUtils.isEmpty(pass)  ||   TextUtils.isEmpty(email) )  {

                Snackbar.make(it,"Please enter all fields", Snackbar.LENGTH_SHORT).show()
            }else{
                login(email,pass)
            }
        }





    }

    private fun login(email:String,pass:String){
        mAuth.signInWithEmailAndPassword(email,pass)
    .addOnCompleteListener { task->
        if(task.isSuccessful){


            Toast.makeText(context, mAuth.currentUser?.email.toString(), Toast.LENGTH_LONG).show()

                startActivity(Intent(context,DashActivity::class.java))



        }else{


          Toast.makeText(context,task.exception?.localizedMessage.toString(),Toast.LENGTH_LONG).show()

        }

    }



    }



}