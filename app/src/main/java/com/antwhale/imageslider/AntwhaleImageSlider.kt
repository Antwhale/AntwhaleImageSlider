package com.antwhale.imageslider

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.antwhale.view.imageslider.R
import com.antwhale.view.imageslider.databinding.AntwhaleImageSliderBinding

class AntwhaleImageSlider(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var layoutSliderIndicators: LinearLayout
    private lateinit var constraintLayout: ConstraintLayout

    private var bottomMargin : Int
    private var indicatorMargin : Int
    private var activeIndicatorRes : Int
    private var inactiveIndicatorRes : Int
    private var isAutoScrolling  = false
    private var scrollingDelay  = 2000

    private var scrollHandler = Handler(Looper.getMainLooper())

    private val autoScrolling = Runnable {
        val currentIndex = viewPager2.currentItem

        viewPager2.setCurrentItem(currentIndex + 1, true)

        startAutoScrolling()
    }

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.antwhale_image_slider, this, true)

        viewPager2 = findViewById(R.id.viewPager2)
        layoutSliderIndicators = findViewById(R.id.layoutSliderIndicators)
        constraintLayout = findViewById(R.id.constraintLayout)

        //attrs.xml에서 View의 속성을 가져온다.
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AntwhaleImageSlider)
        indicatorMargin = typedArray.getDimensionPixelSize(R.styleable.AntwhaleImageSlider_indicatorMargin, resources.getDimensionPixelSize(R.dimen.default_indicator_margin))
        bottomMargin = typedArray.getDimensionPixelSize(R.styleable.AntwhaleImageSlider_bottomMargin, resources.getDimensionPixelSize(R.dimen.default_bottom_margin))
        activeIndicatorRes = typedArray.getResourceId(R.styleable.AntwhaleImageSlider_activeIndicatorRes, R.drawable.indicator_active)
        inactiveIndicatorRes = typedArray.getResourceId(R.styleable.AntwhaleImageSlider_inactiveIndicatorRes, R.drawable.indicator_inactive)
        isAutoScrolling = typedArray.getBoolean(R.styleable.AntwhaleImageSlider_autoScrolling,false)

        typedArray.recycle()

        initViewPager2()
    }

    private fun initIndicator(imgCount: Int) {

        val indicators = arrayOfNulls<ImageView>(imgCount)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(indicatorMargin, 0, indicatorMargin, 0)
        for(index in indicators.indices) {
            indicators[index] = ImageView(context)
            indicators[index]?.setImageDrawable(ContextCompat.getDrawable(context, inactiveIndicatorRes))
            indicators[index]?.layoutParams = layoutParams
            layoutSliderIndicators.addView(indicators[index])
        }

        val constraints = ConstraintSet()
        constraints.clone(constraintLayout)
        constraints.connect(R.id.layoutSliderIndicators, ConstraintSet.START, R.id.constraintLayout, ConstraintSet.START)
        constraints.connect(R.id.layoutSliderIndicators, ConstraintSet.END, R.id.constraintLayout, ConstraintSet.END)
        constraints.connect(R.id.layoutSliderIndicators, ConstraintSet.BOTTOM, R.id.constraintLayout, ConstraintSet.BOTTOM, bottomMargin)
        constraints.applyTo(constraintLayout)

        setCurrentSliderIndicator(0)
    }

    private fun setCurrentSliderIndicator(position: Int) {
        val childCount = layoutSliderIndicators.childCount
        for(index in 0 until childCount) {
            val imageView = layoutSliderIndicators.getChildAt(index) as ImageView
            val realPosition = (viewPager2.adapter as AntwhaleImageSliderAdapter<*, *>).getRealPosition(position)
            if(index == realPosition) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(context, activeIndicatorRes)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(context, inactiveIndicatorRes)
                )
            }
        }
    }

    private fun initViewPager2() {
        viewPager2.apply {
            offscreenPageLimit = 1

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentSliderIndicator(position)
                }
            })
        }
    }

    fun setAdapter(antwhaleImageSliderAdapter: AntwhaleImageSliderAdapter<*, *>) {
        viewPager2.adapter = antwhaleImageSliderAdapter
        initIndicator(antwhaleImageSliderAdapter.sliderImages.size)

        if(isAutoScrolling) startAutoScrolling()
    }

    private fun startAutoScrolling(){
        scrollHandler.removeCallbacks(autoScrolling)
        scrollHandler.postDelayed(autoScrolling, scrollingDelay.toLong())
    }

    private fun stopAutoScrolling() {
        scrollHandler.removeCallbacks(autoScrolling)
    }

    companion object {
        const val TAG = "AntwhaleImageSlider"
    }
}