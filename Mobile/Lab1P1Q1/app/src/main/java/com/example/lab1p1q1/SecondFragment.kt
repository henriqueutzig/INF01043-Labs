package com.example.lab1p1q1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.lab1p1q1.databinding.FragmentSecondBinding
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // write arg to header
        val args: SecondFragmentArgs by navArgs()
        val count = args.randNum
        val countTxt = getString(R.string.second_fragment_label, count)

        view.findViewById<TextView>(R.id.textview_header).text = countTxt

        // generate and diplay rand num
        val rand = java.util.Random()
        var randNum = 0
        if (count > 0)
        {
            randNum = rand.nextInt(count+1)
        }
        view.findViewById<TextView>(R.id.textview_random).text = randNum.toString()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}