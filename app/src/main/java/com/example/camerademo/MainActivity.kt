package com.example.camerademo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

private const val REQUEST_CODE = 42
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var btnCamera : Button
    private lateinit var imageView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnCamera = findViewById(R.id.btnCamera)

        btnCamera.setOnClickListener {
            // On click camera app will open
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(this.packageManager) != null){
                startActivityForResult(intent, REQUEST_CODE )
            }else{
                Toast.makeText(this, "Camera App not Installed....",Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(takenImage)

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}