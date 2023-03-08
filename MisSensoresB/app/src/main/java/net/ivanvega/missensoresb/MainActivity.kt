package net.ivanvega.missensoresb

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mSensor: Sensor? = null
    private lateinit var sensorManager: SensorManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        deviceSensors.forEach {
            Log.i("MisSensores", it.toString())
        }
        Log.i("MisSensores", deviceSensors.size.toString())
        val sensorMagnr = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        if (sensorMagnr != null) {
            // Success! There's a magnetometer.
            Toast.makeText(this, "Hay magnetometro", Toast.LENGTH_LONG).show()
            Log.i("MisSensores", "Hay magnetometro")
        } else {
            // Failure! No magnetometer.
            Log.i("MisSensores", "NO Hay magnetometro")
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            val gravSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_GRAVITY)
            // Use the version 3 gravity sensor.
            mSensor =
                gravSensors.firstOrNull { it.vendor.contains("Google LLC") && it.version == 3 }
        }

        if (mSensor == null) {
            // Use the accelerometer.
            mSensor = if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            } else {
                // Sorry, there are no accelerometers on your device.
                // You can't play this game.
                null
            }


        }
    }
}