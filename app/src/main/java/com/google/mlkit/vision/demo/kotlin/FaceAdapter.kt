package com.google.mlkit.vision.demo.kotlin

import android.graphics.Bitmap
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.vision.demo.ApiService
import com.google.mlkit.vision.demo.BitmapUtils
import com.google.mlkit.vision.demo.R

class FaceAdapter(private val rootImage: Bitmap?, private val faces: ArrayList<Rect>?) :
        RecyclerView.Adapter<FaceAdapter.MyViewHolder>() {

    class MyViewHolder(val rootView: ViewGroup) : RecyclerView.ViewHolder(rootView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.face_item, parent, false) as ViewGroup
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var bitmap: Bitmap? = null
        if (faces.isNullOrEmpty().not()) {
            bitmap = BitmapUtils.cropBitmap(rootImage, faces?.get(position))
            rootImage?.let {
                holder.rootView
                        .findViewById<ImageView>(R.id.faceView)
                        .setImageBitmap(bitmap)
            }
        }
        holder.rootView.findViewById<Button>(R.id.save).setOnClickListener {
            val requestBitmap = bitmap
            val requestFaceDescription = holder.rootView.findViewById<EditText>(R.id.description).text
            ApiService.saveFaceWithName(requestBitmap, requestFaceDescription.toString())
        }
    }

    override fun getItemCount() = faces?.size ?: 0
}
