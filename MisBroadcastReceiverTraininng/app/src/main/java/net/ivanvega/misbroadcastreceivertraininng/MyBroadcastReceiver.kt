package net.ivanvega.misbroadcastreceivertraininng

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.provider.Telephony
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast


private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }

        when (intent.action) {
            "android.provider.Telephony.SMS_RECEIVED" -> {
                Toast.makeText(context, "SI RECIBIO EL MENSAJE", Toast.LENGTH_LONG).show()
            }
        }

        //mensajeria
        var strMensaje = ""
        if (intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            val bndSMS = intent.extras
            val pdus = bndSMS!!["pdus"] as Array<Any>?
            val smms: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus!!.size)
            for (i in smms.indices) {
                smms[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                strMensaje = "Mensaje: " + smms[i]!!.getOriginatingAddress() + "\n" +
                        smms[i]!!.getMessageBody().toString();

            }
            Log.d("MiBroadcast", strMensaje)
            Toast.makeText(context, strMensaje, Toast.LENGTH_LONG).show()
        }

        //telefono
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (state != null) {
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                var incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if (incomingNumber != null) {
                    val incoming_number = incomingNumber;
                    Toast.makeText(context, incoming_number, Toast.LENGTH_SHORT).show();
                }
            }

            /*val pendingResult: PendingResult = goAsync()
            val asyncTask = Task(pendingResult, intent)
            asyncTask.execute()*/
        }

        class Task(
            private val pendingResult: PendingResult,
            private val intent: Intent
        ) : AsyncTask<String, Int, String>() {

            override fun doInBackground(vararg params: String?): String {
                val sb = StringBuilder()
                sb.append("Action: ${intent.action}\n")
                sb.append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
                return toString().also { log ->
                    Log.d(TAG, log)
                }
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                // Must call finish() so the BroadcastReceiver can be recycled.
                pendingResult.finish()
            }
        }
    }
}