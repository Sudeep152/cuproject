package com.shashank.wosafe.Screen.auth

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.shashank.wosafe.R
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regtolog.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        firebaseAuth = FirebaseAuth.getInstance()



        regBtn.setOnClickListener {
            val name = nameEdt.text.toString()
            val email = emailEdt.text.toString()
            val pass = passEdt.text.toString()

            if(TextUtils.isEmpty(name)||
                TextUtils.isEmpty(pass)  ||   TextUtils.isEmpty(email) )  {

                Snackbar.make(it,"Please enter all fields", Snackbar.LENGTH_SHORT).show()
            }else{
                regisNew(email,pass,name)
            }

        }



    }

    fun regisNew(email: String?, password: String?, name: String?){
        firebaseAuth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    val user1 = firebaseAuth.currentUser
                    val cq1 : UserProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                    user1?.updateProfile(cq1)
                    Toast.makeText(
                        context,
                        "Signup Done:",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                } else {
                    Toast.makeText(
                        context,
                        "Signup Failure: ${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    /*fun changepassword(email: String?){
        firebaseAuth.sendPasswordResetEmail(email.toString()).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(application,"Password Reset Email Sent",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(application,"Enter Valid Email ID",Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    fun signOut() {
        //firebaseAuth.signOut()

        //Toast.makeText(application,"Logged Out",Toast.LENGTH_SHORT).show()
    }


}