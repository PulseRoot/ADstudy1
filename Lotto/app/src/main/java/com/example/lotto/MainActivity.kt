package com.example.lotto
//gradle에 implement http://airbnb.io/lottie/#/android 참고
//애니메이션 Json파일은 lottiefiles.com에서 다운로드함.
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lotteryNumbers = arrayListOf(number1,number2,number3,number4,number5,number6)

        //버튼 클릭후 랜덤숫자 뽑기 이전까지 화면에 보이는 숫자가 계속해서 변환하도록 하는 역할 (시각적으로 심심하지말라고)
        val countDownTimer = object: CountDownTimer(3000, 100){
            //3초 중에 얼마나 남았는지
            override fun onTick(millisUntilFinished: Long) {
                lotteryNumbers.forEach{
                    val randomNumber = (Math.random() * 45 + 1).toInt() //1~45사의의 랜덤한 숫자발생
                    it.text = "$randomNumber"
                }
            }
            override fun onFinish() {
            }
        }

        lotteryButton.setOnClickListener{
            if(lotteryButton.isAnimating){
                //버튼이 애니메이팅 중이라면?
             lotteryButton.cancelAnimation()
             countDownTimer.cancel()
                //애니메이션과 타이머를 둘다 종료 시킨다.
            }else {
                lotteryButton.playAnimation()
                countDownTimer.start()
            }
        }
    }
}