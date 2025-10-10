package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.BannerAd
import com.adxcorp.ads.common.AdConstants

class CloseAdActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + CloseAdActivity::class.java.simpleName
    }

    private var mBannerAd: BannerAd? = null
    private var mCloseDialog: CloseAdDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mCloseDialog?.show()
            }
        })

        try {
            if (mBannerAd == null) {
                mBannerAd = BannerAd(this, getString(R.string.mrec_unit_id), AdConstants.BANNER_AD_SIZE.AD_SIZE_300x250)
                mBannerAd?.setBannerListener(object : BannerAd.BannerListener {
                    override fun onAdLoaded() {
                        Log.d(TAG, "Banner onAdLoaded")
                    }

                    override fun onAdError(errorCode: Int) {
                        Log.d(TAG, "Banner onAdError : $errorCode")
                    }

                    override fun onAdClicked() {
                        Log.d(TAG, "Banner onAdClicked")
                    }
                })

                mBannerAd?.loadAd()
                mCloseDialog = CloseAdDialog.Builder(this).setBannerAd(mBannerAd).create()
                mCloseDialog?.setPositiveButtonClickListener { _, _ -> finish() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mBannerAd?.destroy()
        mBannerAd = null
    }
}