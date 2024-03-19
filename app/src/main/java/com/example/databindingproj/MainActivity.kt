package com.example.databindingproj

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import com.example.databindingproj.databinding.ActivitMainBinding
import com.example.databindingproj.ui.theme.DataBindingProjTheme

class MainActivity : ComponentActivity() {

    lateinit var splashBinding : ActivitMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding = DataBindingUtil.setContentView(this, R.layout.activit_main)
        splashBinding.textView.text = "Splash Screen"

        Handler().postDelayed({
            val i = Intent(this@MainActivity, LoginScreen::class.java)
            startActivity(i)
            finish()
        }, 2000)

    }
}