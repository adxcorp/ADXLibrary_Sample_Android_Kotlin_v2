package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.InterstitialAd

class InterstitialActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + InterstitialActivity::class.java.simpleName
    }

    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (interstitialAd?.isLoaded == true) {
                interstitialAd!!.show()
            }
        }

        interstitialAd = InterstitialAd(this, getString(R.string.interstitial_unit_id))
        interstitialAd?.setInterstitialListener(object : InterstitialAd.InterstitialListener {
            override fun onAdLoaded() {
                Log.d(TAG, "Interstitial onAdLoaded")
                Toast.makeText(this@InterstitialActivity, "onAdLoaded", Toast.LENGTH_LONG).show()
            }

            override fun onAdError(errorCode: Int) {
                Log.d(TAG, "Interstitial onAdError : $errorCode")
                Toast.makeText(this@InterstitialActivity, "onAdError : $errorCode", Toast.LENGTH_LONG).show()
            }

            override fun onAdClicked() {
                Log.d(TAG, "Interstitial onAdClicked")
            }

            override fun onAdImpression() {
                Log.d(TAG, "Interstitial onAdImpression")
            }

            override fun onAdClosed() {
                Log.d(TAG, "Interstitial onAdClosed")
            }

            override fun onAdFailedToShow() {
                Log.d(TAG, "Interstitial onAdFailedToShow")
            }
        })
        Log.d(TAG, "Interstitial loadAd")
        interstitialAd?.loadAd()
    }

    override fun onDestroy() {
        super.onDestroy()

        interstitialAd?.destroy()
        interstitialAd = null
    }
}