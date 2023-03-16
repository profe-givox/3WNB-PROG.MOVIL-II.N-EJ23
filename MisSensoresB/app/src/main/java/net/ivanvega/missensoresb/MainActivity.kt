package net.ivanvega.missensoresb

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {
     var pelota: MiViewDibujado? = null
    private val linear_acceleration= FloatArray(3)
    private val gravity = FloatArray(3)
    private var mLastAccelerometerSet: Boolean? = false
    private var sensorCampoMagetico: Sensor? = null
    private var sensorAcelerometro: Sensor? = null
    private var mLight: Sensor? = null
    private var mSensor: Sensor? = null
    private lateinit var sensorManager: SensorManager

     var xPos =
        0f
      var xAcceleration:kotlin.Float = 0f
      var xVelocity:kotlin.Float = 0.0f
     var yPos =        0f
      var yAcceleration:kotlin.Float = 0f
      var yVelocity:kotlin.Float = 0.0f

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
         pelota = MiViewDibujado(this)
        setContentView(pelota)



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
            sensorManager.registerListener(pelota,
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
        sensorManager.unregisterListener(pelota)
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


                var xAcc: Float = p0.values.get(0)
                var yAcc: Float = p0.values.get(1)
                var zAcc: Float = p0.values.get(2)

                //Log.i("Acele", "x=$xAcc ; y=$yAcc ; z=$zAcc")

            }

            Sensor.TYPE_MAGNETIC_FIELD -> {

            }

            else  -> {

            }

        }
    }

    private fun updatePOsicion( xOrientation: Float, yOrientation : Float) {
        xAcceleration = xOrientation;
        yAcceleration = yOrientation;
        updateX();
        updateY();
    }

    fun updateX() {
        xVelocity -= xAcceleration * 0.3f
        xPos += xVelocity
    }

    fun updateY() {
        yVelocity -= yAcceleration * 0.3f
        yPos += yVelocity
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }

}

class  MiViewDibujado(var micontext: Context)
    : View(micontext), SensorEventListener {

    private val linear_acceleration= FloatArray(3)
    private val gravity = FloatArray(3)
    /*var width : Int  = 0
    var height : Int  = 0*/

    var xPos =
        0f
    var xAcceleration:kotlin.Float = 0f
    var xVelocity:kotlin.Float = 0.0f
    var yPos =        0f
    var yAcceleration:kotlin.Float = 0f
    var yVelocity:kotlin.Float = 0.0f


    init {
        val windowManager = (getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        val screen: Display =
            (getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        var width = screen.getWidth()
        var height = screen.getHeight()

        Log.d("TamañoPantall", "w=$width ; h=$height"  )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            height = windowManager.currentWindowMetrics.bounds.height()
            width = windowManager.currentWindowMetrics.bounds.width()
        }

        Log.d("TamañoPantall", "w=$width ; h=$height"  )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            height = displayMetrics.heightPixels
            width = displayMetrics.widthPixels
        }

        Log.d("TamañoPantall", "w=$width ; h=$height"  )
    }

    val pincel = Paint().also {
        it.setColor(Color.RED)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(200F,200F, 400F,400.0F, pincel)
        canvas?.drawCircle(xPos,yPos, 50.0F , pincel)
        invalidate()
    }

    private fun updatePOsicion( xOrientation: Float, yOrientation : Float) {
        xAcceleration = xOrientation;
        yAcceleration = yOrientation;
        updateX();
        updateY();
    }

    fun updateX() {
        xVelocity -= xAcceleration * 0.3f
        xPos += xVelocity
    }

    fun updateY() {
        yVelocity -= yAcceleration * 0.3f
        yPos += yVelocity
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        val alpha: Float = 0.8f

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * p0!!.values[0]
        gravity[1] = alpha * gravity[1] + (1 - alpha) * p0!!.values[1]
        gravity[2] = alpha * gravity[2] + (1 - alpha) * p0!!.values[2]

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = p0!!.values[0] - gravity[0]
        linear_acceleration[1] = p0!!.values[1] - gravity[1]
        linear_acceleration[2] = p0!!.values[2] - gravity[2]

        var xAcc = linear_acceleration[0]
        var yAcc = linear_acceleration[1]
        var zAcc = linear_acceleration[2]

        Log.i("Acele", "x=$xAcc ; y=$yAcc ; z=$zAcc")

        /*findViewById<TextView>(R.id.txtAcelerometer).let {
            it.text = "x=$xAcc ; y=$yAcc ; z=$zAcc"
            Log.i("Acele", "x=$xAcc ; y=$yAcc ; z=$zAcc")
        }*/
        updatePOsicion(xAcc,(yAcc*-1))
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}
