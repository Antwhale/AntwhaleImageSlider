package com.antwhale.imageslider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.antwhale.view.imageslider.R
import com.antwhale.view.imageslider.databinding.AntwhaleImageSliderBinding

class AntwhaleImageSlider(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private lateinit var binding: AntwhaleImageSliderBinding
    private var canvas: Canvas? = null
    private var imgCount: Int
    private val inActivePaint by lazy {
        Paint().apply {
            color = Color.GRAY
            style = Paint.Style.FILL
        }
    }
    private val activePaint by lazy {
        Paint().apply {
            color = Color.BLACK
            style = Paint.Style.FILL
        }
    }

    init {
        Log.d(TAG, "AntwhaleImageSlider init ")
        binding = AntwhaleImageSliderBinding.inflate(LayoutInflater.from(context), this, true)

        //attrs.xml에서 View의 속성을 가져온다.
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AntwhaleImageSlider)

        imgCount = typedArray.getInt(R.styleable.AntwhaleImageSlider_imgCount, 1)

        typedArray.recycle()
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")

        this.canvas = canvas

        val space = (width / imgCount).toFloat()
        val circleY = /*height - 100.toFloat()*/ (height / 2).toFloat()

        for (i in 1..imgCount) {
            //원(indicator) 그리기
            if(i == 1) {
                canvas?.drawCircle(space * i, circleY, RADIUS, activePaint)
            } else {
                canvas?.drawCircle(space * i, circleY, RADIUS, inActivePaint)
            }
        }

    }

    companion object {
        const val TAG = "AntwhaleImageSlider"
        const val RADIUS = 10f
    }
}