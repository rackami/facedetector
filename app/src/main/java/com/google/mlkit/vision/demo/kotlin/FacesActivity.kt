package com.google.mlkit.vision.demo.kotlin

import android.R.attr.src
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.vision.demo.R
import java.io.FileNotFoundException


class FacesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faces)

        supportActionBar?.title = "Faces Detected"

        val currentIntent = intent

        var fullImage : Bitmap? = null
        try {
            fullImage = BitmapFactory.decodeStream(openFileInput(currentIntent.getStringExtra("IMAGE_TAG")))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val faces = currentIntent.getParcelableArrayListExtra<Rect>("FACE_LIST_TAG")

        viewManager = LinearLayoutManager(this)
        viewAdapter = FaceAdapter(fullImage, faces)

        recyclerView = findViewById<RecyclerView>(R.id.facesList).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }
}