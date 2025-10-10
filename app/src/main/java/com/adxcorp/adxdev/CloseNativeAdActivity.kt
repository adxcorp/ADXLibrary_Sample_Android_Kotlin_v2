package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.nativeads.AdxCloseAdFactory

class CloseNativeAdActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + CloseNativeAdActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AdxCloseAdFactory.showCloseAd(this@CloseNativeAdActivity, { _, _ ->
                    Log.d(TAG, "onClick positive button")
                    finish()
                }) { Log.d(TAG, "onCancel") }
            }
        })

        AdxCloseAdFactory.init(this, getString(R.string.native_close_unit_id), "")
        AdxCloseAdFactory.preloadAd()
    }

    override fun onDestroy() {
        // Optional
        // AdxCloseAdFactory.destroy()

        super.onDestroy()
    }
}