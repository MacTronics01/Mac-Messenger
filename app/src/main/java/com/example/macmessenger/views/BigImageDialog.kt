package com.example.macmessenger.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.macmessenger.R
import kotlinx.android.synthetic.main.image_dialog_layout.view.*

class BigImageDialog:DialogFragment() {

    lateinit var bigImageView:ImageView

    private var imageUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            imageUrl = requireArguments().getString("url").toString()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.image_dialog_layout, container, false)
        this.dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val circularProgressDrawable = this.dialog?.let { CircularProgressDrawable(it.context) }
        circularProgressDrawable?.strokeWidth = 5f
        circularProgressDrawable?.centerRadius = 30f
        circularProgressDrawable?.start()

        val requestOptions = RequestOptions().placeholder(circularProgressDrawable)

        bigImageView
        this.dialog?.let {
            Glide.with(it.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(v.bigImageView)
        }

        return v
    }


    companion object {
        @JvmStatic
        fun newInstance(imageUrl: String) =
                BigImageDialog().apply {
                    arguments = Bundle().apply {
                        putString("url", imageUrl)
                    }
                }
    }
}