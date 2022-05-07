package com.example.belajarfirebase2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.belajarfirebase2.fragment.LoginFragment
import com.example.belajarfirebase2.fragment.RegisterFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            // User is signed in
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val pagerAdapter = AuthenticationPagerAdapter(
            supportFragmentManager
        )
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())
        viewPager.adapter = pagerAdapter
    }

    internal class AuthenticationPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        private val fragmentList = ArrayList<Fragment>()
        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }
    }
}