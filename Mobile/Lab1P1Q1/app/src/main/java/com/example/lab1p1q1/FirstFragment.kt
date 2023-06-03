package com.example.lab1p1q1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab1p1q1.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSum.setOnClickListener {
            val n1 = binding.inNum1.text.toString()
            var num1 : Float = 0F
            if (n1 != ""){
                num1 = n1.toFloat()
            }
            val n2 = binding.inNum2.text.toString()
            var num2 : Float = 0F
            if (n2 != ""){
                num2 = n2.toFloat()
            }
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)


            binding.tvSumRes.text = "Sum Result = " + (num1+num2).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}