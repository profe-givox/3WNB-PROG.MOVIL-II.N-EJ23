package net.ivanvega.misbroadcastreceivertraininng

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val br: BroadcastReceiver = MyBroadcastReceiver()
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(br, filter)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

}