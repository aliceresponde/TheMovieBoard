package com.aliceresponde.themovieboard.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aliceresponde.themovieboard.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavGraph()
    }

    fun initNavGraph() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottonMenu.setupWithNavController(navController)
    }
}