package com.antwhale.rolling_banner.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antwhale.imageslider.AntwhaleImageSlider

class MainActivity : AppCompatActivity() {
    val antwhaleImageSlider : AntwhaleImageSlider by lazy { findViewById(R.id.imageSlider) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/03/08/21/41/landscape-4913841_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg"
        )

        val rollingBannerAdapter = RollingBannerAdapter(images)
        antwhaleImageSlider.setAdapter(rollingBannerAdapter)

    }
}