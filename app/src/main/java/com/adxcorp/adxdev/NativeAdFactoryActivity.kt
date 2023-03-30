package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.adxcorp.ads.nativeads.*

class NativeAdFactoryActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + NativeAdFactoryActivity::class.java.simpleName
    }

    private var mContentView: LinearLayout? = null
    private var mAdView: View? = null
    private var mNativeAd: NativeAd? = null
    private var mAdxUnitId: String? = null

    private val mListener: AdxNativeAdFactory.NativeAdListener = object : AdxNativeAdFactory.NativeAdListener {
        override fun onSuccess(s: String, nativeAd: NativeAd) {
            Log.d(TAG, "onSuccess")
            if (mAdxUnitId == s) {
                mNativeAd = nativeAd
                mAdView = AdxNativeAdFactory.getNativeAdView(
                    this@NativeAdFactoryActivity,
                    mAdxUnitId,
                    mContentView,
                    object : NativeAd.NativeEventListener {
                        override fun onImpression(view: View) {
                            Log.d(TAG, "onImpression")
                        }

                        override fun onClick(view: View) {
                            Log.d(TAG, "onClick")
                        }
                    })
                mContentView?.addView(mAdView)
            }
        }

        override fun onFailure(s: String) {
            Log.d(TAG, "onFailure")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad_factory)

        mContentView = findViewById(R.id.content_main)
        mAdxUnitId = getString(R.string.native_unit_id)

        AdxNativeAdFactory.addListener(mListener)
        AdxNativeAdFactory.loadAd(mAdxUnitId)
    }

    override fun onDestroy() {
        super.onDestroy()

        AdxNativeAdFactory.removeListener(mListener)

        mNativeAd?.destroy()
        mNativeAd = null
    }
}