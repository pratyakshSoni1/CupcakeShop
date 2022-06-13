package com.smartphone_codes.cupcakeshop.mainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smartphone_codes.cupcakeshop.R
import com.smartphone_codes.cupcakeshop.databinding.FragmentTimeBinding
import com.smartphone_codes.cupcakeshop.model.OrderViewModel
import java.util.*


class FragmentTime : Fragment() {

    private lateinit var binding : FragmentTimeBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_time,container,false)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            fragmentTime=this@FragmentTime
            viewModel = sharedViewModel
            lifecycleOwner=viewLifecycleOwner

        }
    }


    //For going to Summary Fragment
    fun goToNextScreen() {
        Toast.makeText(activity, "Next", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_fragment_time_to_fragment_summary)
    }


    fun cancelOrder() {
        Toast.makeText(activity, "Cancel Order", Toast.LENGTH_SHORT).show()
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_fragment_time_to_fragment_main)
    }

}