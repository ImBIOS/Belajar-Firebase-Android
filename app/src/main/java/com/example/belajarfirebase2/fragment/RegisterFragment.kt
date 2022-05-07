package com.example.belajarfirebase2.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.belajarfirebase2.MainActivity
import com.example.belajarfirebase2.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validate email
            if (email.isEmpty()){
                binding.etEmail.error = "Email Harus Diisi"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }

            // Validate email not valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etPassword.error = "Email Tidak Valid"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // Validate password
            if (password.isEmpty()){
                binding.etPassword.error = "Password Harus Diisi"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.etPassword.error = "Password Minimal 6 Karakter"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            firebaseRegister(email,password)
        }
    }

    private fun firebaseRegister(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Register success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}