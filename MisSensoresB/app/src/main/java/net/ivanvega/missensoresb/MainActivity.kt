package net.ivanvega.missensoresb

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var mLastAccelerometerSet: Boolean? = false
    private var sensorCampoMagetico: Sensor? = null
    private var sensorAcelerometro: Sensor? = null
    private var mLight: Sensor? = null
    private var mSensor: Sensor? = null
    private lateinit var sensorManager: SensorManager


    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)


    val sensorEventListener : SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(p0: SensorEvent?) {
            //TODO("Not yet implemented")
            // The light sensor returns a single value.
            // Many sensors return 3 values, one for each axis.
            val lux = p0!!.values[0]
            // Do something with this sensor value.
            Log.i("UnSensor",lux.toString())
            //findViewById<TextView>(R.id.txt).text = lux.toString()
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            //TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MiViewDibujado(context = this))

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
        Log.i("UnSensor", mSensor.toString())
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        Log.i("UnSensor", mLight.toString())

        sensorAcelerometro = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER)
        sensorCampoMagetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onResume() {
        super.onResume()
        mLight?.also {
            sensorManager.registerListener(sensorEventListener,
                it,
                SensorManager.SENSOR_DELAY_NORMAL )
        }
        sensorAcelerometro?.also {
            sensorManager.registerListener(this,
                it,
                SensorManager.SENSOR_DELAY_UI)
        }

        sensorCampoMagetico?.also {
            sensorManager.registerListener(
                this, it,
                SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        //TODO("Not yet implemented")
        when(p0?.sensor?.getType()){

            Sensor.TYPE_ACCELEROMETER -> {

                System.arraycopy(
                    p0.values, 0, accelerometerReading,
                    0, accelerometerReading.size
                )
                mLastAccelerometerSet = true


                val xAcc: Float = p0.values.get(0)
                val yAcc: Float = p0.values.get(1)
                val zAcc: Float = p0.values.get(2)

                /*findViewById<TextView>(R.id.txtAcelerometer).let {
                    it.text = "x=$xAcc ; y=$yAcc ; z=$zAcc"
                    Log.i("Acele", "x=$xAcc ; y=$yAcc ; z=$zAcc")
                }*/

                updatePOsicion()
            }

            Sensor.TYPE_MAGNETIC_FIELD -> {

            }

            else  -> {

            }

        }
    }

    private fun updatePOsicion() {

    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }

}

class  MiViewDibujado(context: Context)
    : View(context) {

    val pincel = Paint().also {
        it.setColor(Color.RED)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(200F,200F, 400F,400.0F, pincel)
        invalidate()
    }

}