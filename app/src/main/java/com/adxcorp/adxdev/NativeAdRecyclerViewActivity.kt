package com.adxcorp.adxdev

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adxcorp.ads.nativeads.AdxNativeAdFactory
import com.adxcorp.ads.nativeads.AdxRecyclerAdapter
import com.adxcorp.ads.nativeads.event.NativeAdLoadedListener
import com.adxcorp.ads.nativeads.position.NativeAdPosition
import java.util.*

class NativeAdRecyclerViewActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ADX:" + NativeAdRecyclerViewActivity::class.java.simpleName
    }

    private var mRecyclerAdapter: AdxRecyclerAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private val mList: MutableList<String> = ArrayList()
    private var mAdxUnitId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad_recyclerview)

        mAdxUnitId = getString(R.string.native_unit_id)
        for (i in 0..149) {
            mList.add(String.format(Locale.US, "Content Item #%d", i))
        }

        mRecyclerView = findViewById<View>(R.id.native_recycler_view) as RecyclerView

        val originalAdapter: RecyclerView.Adapter<*> = DemoRecyclerAdapter()

        val clientPosition = NativeAdPosition.ClientPosition()
        clientPosition.addFixedPosition(2)
        clientPosition.enableRepeatingPositions(5)

        mRecyclerAdapter = AdxNativeAdFactory.getAdxRecyclerAdapter(this, originalAdapter, mAdxUnitId, clientPosition)
        mRecyclerAdapter!!.setAdLoadedListener(object : NativeAdLoadedListener {
            override fun onAdLoaded(i: Int) {
                Log.d(TAG, "onAdLoaded : $i")
            }

            override fun onAdRemoved(i: Int) {
                Log.d(TAG, "onAdRemoved : $i")
            }
        })

        mRecyclerAdapter!!.setContentChangeStrategy(AdxRecyclerAdapter.ContentChangeStrategy.MOVE_ALL_ADS_WITH_CONTENT)
        mRecyclerView!!.adapter = mRecyclerAdapter
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mRecyclerAdapter!!.loadAds(mAdxUnitId!!)
    }

    override fun onDestroy() {
        super.onDestroy()

        mRecyclerAdapter?.destroy()
    }

    private inner class DemoRecyclerAdapter : RecyclerView.Adapter<DemoViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DemoViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return DemoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
            holder.textView.text = mList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItemCount(): Int {
            return mList.size
        }
    }

    private class DemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById<View>(android.R.id.text1) as TextView
        }
    }
}