package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        val runnable= Runnable {  val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() }
        handler.postDelayed(runnable, 3000)//3초동안 대기시 자동으로 인텐트

        //gradle에 플러그인을 선언함을 통해 굳이 변수로 할당안해서 xml id값으로 불러오기 가능
        animationView.setOnClickListener {
            handler.removeCallbacks(runnable) //클릭을 한다면 runnable을 삭제해서 두번 인텐트되지 않도록 막음
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}