package com.example.belajarfirebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.belajarfirebase2.databinding.ActivityMainBinding
import com.example.belajarfirebase2.adapter.ViewPagerAdapter
import com.example.belajarfirebase2.fragment.HomeFragment
import com.example.belajarfirebase2.fragment.UserFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user == null) {
            // No user is signed in
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTab()
    }

    private fun setupTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(UserFragment(),"Profile")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_user)
    }
}