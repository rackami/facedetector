package com.google.mlkit.vision.demo.kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.demo.ApiService
import com.google.mlkit.vision.demo.BitmapUtils
import com.google.mlkit.vision.demo.R
import java.io.FileNotFoundException


class FacesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faces)

        supportActionBar?.title = "Face Detected"

        val currentIntent = intent

        var fullImage : Bitmap? = null
        try {
            fullImage = BitmapFactory.decodeStream(openFileInput(currentIntent.getStringExtra("IMAGE_TAG")))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val faces = currentIntent.getParcelableArrayListExtra<Rect>("FACE_LIST_TAG")

        if (faces.isNullOrEmpty().not()) {
            val face = faces[0]

            var bitmap: Bitmap? = null
            if (faces.isNullOrEmpty().not()) {
                bitmap = BitmapUtils.cropBitmap(fullImage, face)
                findViewById<ImageView>(R.id.faceImage)
                        .setImageBitmap(bitmap)
            }
            findViewById<Button>(R.id.save).setOnClickListener {
                val requestBitmap = bitmap
                val requestFaceDescription = findViewById<EditText>(R.id.description).text
                ApiService.saveFaceWithName(requestBitmap, requestFaceDescription.toString()) { isSuccess ->
                    runOnUiThread {
                        if (isSuccess) {
//                            val intent = Intent(applicationContext, FacesActivity::class.java)
//                            intent.putExtra(
//                                    "ggggg",
//                                    "dddd"
//                            )
//                            startActivity(intent)
                        } else {
//                            val intent = Intent(applicationContext, FacesActivity::class.java)
//                            intent.putExtra(
//                                    "ggggg",
//                                    "dddd"
//                            )
//                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}