package com.example.lab1p1q1

import android.Manifest.permission
import android.app.Activity
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.lab1p1q1.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var tvLat : TextView
    private lateinit var tvLong : TextView
    private lateinit var btGetCoords : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        this.btGetCoords = binding.btGetCoords
        ActivityCompat.requestPermissions(this.activity as Activity, arrayOf(permission.ACCESS_FINE_LOCATION), 123)

        this.tvLat = binding.tvLat
        this.tvLong = binding.tvLong

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGetCoords.setOnClickListener {
            val g = GPSTracker(this.context as Context)
            val l: Location? = g.location
            if (l != null) {
                val lat: Double = l.getLatitude()
                val longi: Double = l.getLongitude()
//                Toast.makeText(
//                    this.context as Context, "LAT: " + lat + "LONG: " +
//                            longi, Toast.LENGTH_LONG
//                ).show()
                this.tvLat.text = lat.toString()
                this.tvLong.text = longi.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}