package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generate.setOnClickListener {
            val text = editText.text.toString().trim()
            if (text.isEmpty()){
                Toast.makeText(this,"Add some text", Toast.LENGTH_SHORT).show()
            }else{
            val encode = QRCodeWriter()
                try {
                    val bitMatrix= encode.encode(text,BarcodeFormat.QR_CODE,800,800)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for (x in 0 until width){
                        for (y in 0 until height){
                            bmp.setPixel(x, y, if (bitMatrix[x,y]) Color.BLACK else Color.WHITE )
                        }
                    }
                    image.setImageBitmap(bmp)
                }catch (e: WriterException){


                }                }
        }
    }



}