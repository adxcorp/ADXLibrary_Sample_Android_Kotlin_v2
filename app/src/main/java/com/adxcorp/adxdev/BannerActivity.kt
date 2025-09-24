package com.adxcorp.adxdev

import com.adxcorp.ads.BannerAd
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class BannerActivity : BaseActivity(
    R.layout.activity_banner,
    R.id.cl_banner
) {
    companion object {
        private val TAG = "ADX:" + BannerActivity::class.java.simpleName
    }

    private var bannerAd: BannerAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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