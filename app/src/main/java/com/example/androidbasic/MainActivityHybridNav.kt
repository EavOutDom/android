package com.example.androidbasic

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivityHybridNav : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_hybrid_nav)
    }
}