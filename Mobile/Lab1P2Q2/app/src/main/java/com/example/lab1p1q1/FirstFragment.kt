package com.example.lab1p1q1

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.lab1p1q1.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager

    private var accSensor: Sensor? = null
    private var mLight: Sensor? = null
    private var tempSensor: Sensor? = null

    private var acceleration: FloatArray = FloatArray(3)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        tempSensor =  sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        // Do something with this sensor value
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acceleration = event.values
            binding.tvAccX.text = "x = " + acceleration[0].toBigDecimal().toString()
            binding.tvAccY.text = "y = " + acceleration[1].toBigDecimal().toString()
            binding.tvAccZ.text = "z = " + acceleration[2].toBigDecimal().toString()

            // In this example, alpha is calculated as t / (t + dT),
            // where t is the low-pass filter's time-constant and
            // dT is the event delivery rate.
            //val alpha: Float = 0.8f
            //var gravity : FloatArray = FloatArray(3)
            //var linearAcceleration : FloatArray = FloatArray(3)

            // Isolate the force of gravity with the low-pass filter.
            //gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
            //gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
            //gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]

            // Remove the gravity contribution with the high-pass filter.
            //linearAcceleration[0] = event.values[0] - gravity[0]
            //linearAcceleration[1] = event.values[1] - gravity[1]
            //linearAcceleration[2] = event.values[2] - gravity[2]


        //if (linearAcceleration[0] > 9.81 || linearAcceleration[1] > 9.81 || linearAcceleration[2] > 9.81 )
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            binding.tvLux.text = "lux = " + event.values[0].toBigDecimal().toString()
        }
        else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            binding.tvTemp.text = "Temp = " + event.values[0].toBigDecimal().toString() + "ºC"
        }


    }

    override fun onResume() {
        super.onResume()
        accSensor?.also { acc ->
            sensorManager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL)
        }
        mLight?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
        tempSensor?.also { temp ->
            sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}