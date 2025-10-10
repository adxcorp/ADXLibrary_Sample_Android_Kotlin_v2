@file:Suppress("UNUSED_PARAMETER")

package com.adxcorp.adxdev

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.adxcorp.ads.ADXConfiguration
import com.adxcorp.ads.ADXSdk
import com.adxcorp.ads.nativeads.AdxNativeAdFactory
import com.adxcorp.ads.nativeads.AdxViewBinder
import com.adxcorp.util.ADXLogUtil

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ADX 로그 활성화
        ADXLogUtil.setLogEnable(true)

        // ADX 초기화 관련 설정
        val adxConfiguration = ADXConfiguration.Builder()
            .setAppId(getString(R.string.adx_app_id))
            .setGdprType(ADXConfiguration.GdprType.POPUP_LOCATION)
            .build()

        // ADX Native AD 초기화
        initAdxNative()

        ADXSdk.getInstance().initialize(this as Activity, adxConfiguration) { result, adxConsentState ->
            // 광고 초기화 완료
            Log.d(TAG, "AD(X) SDK initialized, result : $result, adxConsentState : $adxConsentState")
        }
    }

    private fun initAdxNative() {
        AdxNativeAdFactory.init(this)
        AdxNativeAdFactory.setAdxViewBinder(
            getString(R.string.native_unit_id), AdxViewBinder.Builder(R.layout.layout_adx_native_ad)
                .mediaViewContainerId(R.id.mediaContainerId)
                .iconImageId(R.id.adIconId)
                .titleId(R.id.titleId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .callToActionId(R.id.callToActionId)
                .build()
        )
    }

    fun onInterstitial(view: View) {
        val intent = Intent(this, InterstitialActivity::class.java)
        startActivity(intent)
    }

    fun onBanner(view: View) {
        val intent = Intent(this, BannerActivity::class.java)
        startActivity(intent)
    }

    fun onCloseAd(view: View) {
        val intent = Intent(this, CloseAdActivity::class.java)
        startActivity(intent)
    }

    fun onNativeCloseAd(view: View) {
        val intent = Intent(this, CloseNativeAdActivity::class.java)
        startActivity(intent)
    }

    fun onNativeAdFactory(view: View) {
        val intent = Intent(this, NativeAdFactoryActivity::class.java)
        startActivity(intent)
    }

    fun onNativeRecyclerView(view: View) {
        val intent = Intent(this, NativeAdRecyclerViewActivity::class.java)
        startActivity(intent)
    }

    fun onRewardedAd(view: View) {
        val intent = Intent(this, RewardedAdActivity::class.java)
        startActivity(intent)
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    companion object {
        private val TAG = "ADX:" + MainActivity::class.java.simpleName
    }
}