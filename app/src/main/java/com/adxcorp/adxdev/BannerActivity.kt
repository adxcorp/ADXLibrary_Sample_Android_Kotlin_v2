package com.adxcorp.adxdev

import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.BannerAd
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class BannerActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + BannerActivity::class.java.simpleName
    }

    private var bannerAd: BannerAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        bannerAd = findViewById(R.id.banner)
        bannerAd?.setBannerListener(object : BannerAd.BannerListener {
            override fun onAdLoaded() {
                Log.d(TAG, "Banner onAdLoaded")
                Toast.makeText(this@BannerActivity, "onAdLoaded", Toast.LENGTH_LONG).show()
            }

            override fun onAdError(errorCode: Int) {
                Log.d(TAG, "Banner onAdError : $errorCode")
                Toast.makeText(this@BannerActivity, "onAdError : $errorCode", Toast.LENGTH_LONG).show()
            }

            override fun onAdClicked() {
                Log.d(TAG, "Banner onAdClicked")
            }
        })

        Log.d(TAG, "Banner loadAd")
        bannerAd?.loadAd()
    }

    override fun onDestroy() {
        super.onDestroy()

        bannerAd?.destroy()
        bannerAd = null
    }
}