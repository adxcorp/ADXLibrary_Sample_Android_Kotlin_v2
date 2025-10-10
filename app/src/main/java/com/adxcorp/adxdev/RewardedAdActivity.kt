package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.adxcorp.ads.RewardedAd

class RewardedAdActivity : BaseActivity(R.layout.activity_fullscreen) {
    companion object {
        private val TAG = "ADX:" + RewardedAdActivity::class.java.simpleName
    }

    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (rewardedAd?.isLoaded == true) {
                rewardedAd!!.show()
            }
        }

        rewardedAd = RewardedAd(this, getString(R.string.rewarded_ad_unit_id))
        rewardedAd?.setRewardedAdListener(object : RewardedAd.RewardedAdListener {
            override fun onAdLoaded() {
                Log.d(TAG, "RewardedAd onAdLoaded")
                Toast.makeText(this@RewardedAdActivity, "onAdLoaded", Toast.LENGTH_LONG).show()
            }

            override fun onAdError(errorCode: Int) {
                Log.d(TAG, "RewardedAd onAdError : $errorCode")
                Toast.makeText(this@RewardedAdActivity, "onAdError : $errorCode", Toast.LENGTH_LONG).show()
            }

            override fun onAdClicked() {
                Log.d(TAG, "RewardedAd onAdClicked")
            }

            override fun onAdImpression() {
                Log.d(TAG, "RewardedAd onAdImpression")
            }

            override fun onAdClosed() {
                Log.d(TAG, "RewardedAd onAdClosed")
            }

            override fun onAdRewarded() {
                Log.d(TAG, "RewardedAd onAdRewarded")
            }

            override fun onAdFailedToShow() {
                Log.d(TAG, "RewardedAd onAdFailedToShow")
            }
        })

        Log.d(TAG, "RewardedAd loadAd")
        rewardedAd?.loadAd()
    }

    override fun onDestroy() {
        super.onDestroy()

        rewardedAd?.destroy()
        rewardedAd = null
    }
}