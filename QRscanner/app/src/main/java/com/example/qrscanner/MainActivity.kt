package com.example.qrscanner

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startBarcodeReader(view: View){
        IntentIntegrator(this).initiateScan() //QR코드 스캐너가 실행되게하는 메소드. 라이브러리 기본지원됨
    }

    fun startBarcodeReaderCustom(view: View){  //스캔하기전 어플 커스텀
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) //여러 규격 중 QR코드를 스캔할 것이다.
        integrator.setPrompt("QR코드를 스캔해주세요.") //메세지를 띄울 것이다.
        integrator.setCameraId(0) //0 = 후면, 1= 전면 카메라
        integrator.setBeepEnabled(true) //큐알코드 인식하면 삡하는 알림음이 난다.
        integrator.setBarcodeImageEnabled(true) //큐알코드에 담겨있는 정보 뿐만아니라 이미지 역시 비트맵 형태로 전달받는다.
        integrator.initiateScan() //스캔시작
    }

    fun startBarcodeReaderCustomActivity(view: View){  //스캔하기전 어플 커스텀
        val integrator = IntentIntegrator(this)
        integrator.setBarcodeImageEnabled(true) //큐알코드에 담겨있는 정보 뿐만아니라 이미지 역시 비트맵 형태로 전달받는다.
        integrator.captureActivity = MyBarcodeReaderActivity::class.java
        integrator.initiateScan() //스캔시작
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents != null){  //제대로 인식되었다면
                Toast.makeText(this, "scanned: ${result.contents} format: ${result.formatName}", Toast.LENGTH_LONG).show()
            }else{ //실행 중 취소했다면
                Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show()
            }
            if(result.barcodeImagePath != null){ //코드 인식시 전달 받은 이미지가 있다면
                val bitmap = BitmapFactory.decodeFile(result.barcodeImagePath)
                scannedBitmap.setImageBitmap(bitmap)
            }
        }else{ //인식에 실패했다면
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}