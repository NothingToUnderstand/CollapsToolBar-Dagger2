package com.example.molfartask.presentation.subliminal.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.molfartask.R

class BackgroundImageAdapter : RecyclerView.Adapter<BackgroundImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BackgroundImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.back_ground_image, parent, false)
        )

    override fun onBindViewHolder(holder: BackgroundImageViewHolder, position: Int) {

    }

    override fun getItemCount() = Int.MAX_VALUE
}

class BackgroundImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view)