package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.nativeads.AdxCloseAdFactory

class CloseNativeAdActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + CloseNativeAdActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AdxCloseAdFactory.init(this, getString(R.string.native_close_unit_id), "")
        AdxCloseAdFactory.preloadAd()
    }

    override fun onBackPressed() {
        AdxCloseAdFactory.showCloseAd(this, { _, _ ->
            Log.d(TAG, "onClick positive button")
            finish()
        }) { Log.d(TAG, "onCancel") }
    }

    override fun onDestroy() {
        // Optional
        // AdxCloseAdFactory.destroy()

        super.onDestroy()
    }
}