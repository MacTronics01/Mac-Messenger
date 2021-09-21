package com.example.macmessenger

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation)
        val bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        val top: ImageView =findViewById(R.id.logo)
        val middle: TextView =findViewById(R.id.app_name)
        val bottom: TextView =findViewById(R.id.app)


        top.startAnimation(topAnimation)
        middle.startAnimation(middleAnimation)
        bottom.startAnimation(bottomAnimation)

        val splashScreenTimeOut=6000
        val homeIntent= Intent(this@SplashScreen,RegisterActivity::class.java)

        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        },splashScreenTimeOut.toLong())
    }
}




