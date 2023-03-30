package com.adxcorp.adxdev

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.adxcorp.ads.BannerAd

class CloseAdDialog(context: Context?) : Dialog(context!!, android.R.style.Theme_Translucent_NoTitleBar) {

    private var mNegativeButton: TextView? = null
    private var mPositiveButton: TextView? = null
    private var mPositiveButtonListener: View.OnClickListener? = null
    private var mNegativeButtonListener: View.OnClickListener? = null
    private var mBannerAd: BannerAd? = null
    private var mContentLayout: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(R.layout.close_ad_dialog_layout)

        mContentLayout = findViewById<View>(R.id.contentLayout) as RelativeLayout

        if (mBannerAd != null) {
            mContentLayout?.addView(mBannerAd)
        }

        mNegativeButton = findViewById<View>(R.id.negativeButton) as TextView
        mNegativeButton?.setOnClickListener(mNegativeButtonListener)
        mPositiveButton = findViewById<View>(R.id.positiveButton) as TextView
        mPositiveButton?.setOnClickListener(mPositiveButtonListener)
    }

    override fun onBackPressed() {
        return
    }

    fun setPositiveButtonClickListener(listener: DialogInterface.OnClickListener?) {
        mPositiveButtonListener = View.OnClickListener {
            listener?.onClick(this@CloseAdDialog, BUTTON_POSITIVE)
            dismiss()
        }
    }

    fun setNegativeButtonClickListener(listener: DialogInterface.OnClickListener?) {
        mNegativeButtonListener = View.OnClickListener {
            listener?.onClick(this@CloseAdDialog, BUTTON_NEGATIVE)
            dismiss()
        }
    }

    fun setBannerAd(bannerAd: BannerAd?) {
        mBannerAd = bannerAd
    }

    class Builder(private val mContext: Context) {
        private val mNegativeButtonListener: DialogInterface.OnClickListener? = null
        private val mPositiveButtonListener: DialogInterface.OnClickListener? = null
        private var mBannerAd: BannerAd? = null

        fun setBannerAd(bannerAd: BannerAd?): Builder {
            mBannerAd = bannerAd
            return this
        }

        fun create(): CloseAdDialog {
            val dialog = CloseAdDialog(mContext)
            dialog.setNegativeButtonClickListener(mNegativeButtonListener)
            dialog.setPositiveButtonClickListener(mPositiveButtonListener)
            dialog.setBannerAd(mBannerAd)
            return dialog
        }
    }
}