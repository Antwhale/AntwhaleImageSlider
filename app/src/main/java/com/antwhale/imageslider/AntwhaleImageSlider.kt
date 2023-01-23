package com.antwhale.imageslider

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.antwhale.view.imageslider.R
import com.antwhale.view.imageslider.databinding.AntwhaleImageSliderBinding

class AntwhaleImageSlider(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private lateinit var binding : AntwhaleImageSliderBinding

    init {
        binding = AntwhaleImageSliderBinding.inflate(LayoutInflater.from(context), this, true)

        //attrs.xml에서 View의 속성을 가져온다.
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AntwhaleImageSlider)

        val imgCount = typedArray.getInt(R.styleable.AntwhaleImageSlider_imgCount, 1)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }
}