package com.example.android_torch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.content.Intent
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFlashLight()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openFlashLight(){

        val imageView = findViewById<ImageView>(R.id.flashlight) as ImageView
        var powerOn = false

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]

        imageView.setOnClickListener(){
            powerOn = !powerOn

            try {
                if (powerOn) {
                    imageView.setImageResource(R.drawable.ic_flashlight_on)
                    cameraManager.setTorchMode(cameraId, true)
                }
                else {
                    imageView.setImageResource(R.drawable.ic_flashlight_off)
                    cameraManager.setTorchMode(cameraId, false)
                }
            }
            catch (e: Exception){
                var builder = NotificationCompat.Builder(this, "My Notifications")
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("ERROR")
                    .setContentText("Something went wrong")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
        }
    }
}