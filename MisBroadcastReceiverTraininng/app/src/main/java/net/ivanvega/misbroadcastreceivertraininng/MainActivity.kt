package net.ivanvega.misbroadcastreceivertraininng

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var stateSwitch: Boolean = false
    val br: BroadcastReceiver = MyBroadcastReceiver()
    lateinit var  swt : Switch
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        //addAction("android.intent.action.PHONE_STATE")
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swt = findViewById(R.id.switchET)

        stateSwitch = isMyServiceRunning(ServicePhoneStateWithBroadcastReceiver::class.java)

        swt.isChecked = stateSwitch



        swt.setOnClickListener{
            stateSwitch = swt.isChecked()
            if (stateSwitch) {
                val callService = Intent(this,
                    ServicePhoneStateWithBroadcastReceiver::class.java)
                try {
                    startService(callService)
                    Log.d(packageName, "onClick: starting service")
                } catch (ex: Exception) {
                    Log.d(packageName, ex.toString())
                }
            } else {
                stopService(Intent(this,
                    ServicePhoneStateWithBroadcastReceiver::class.java))
                Log.d(packageName, "onClick: stoping service")
            }
        }

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