package com.smartphone_codes.cupcakeshop.mainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smartphone_codes.cupcakeshop.R
import com.smartphone_codes.cupcakeshop.dataSource.dataset
import com.smartphone_codes.cupcakeshop.databinding.FragmentMainBinding
import com.smartphone_codes.cupcakeshop.model.OrderViewModel


class FragmentMain : Fragment() {

    //first of all our viewModel & binding declaration that will be used globally in this fragment class

    private lateinit var binding:FragmentMainBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment & initialize binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //our important layout variable initialization
        binding.apply{

            fragmentMain = this@FragmentMain
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner


        }

    }

    fun orderCupcake() {
        if(sharedViewModel.hasNoFlavourSet()){
            sharedViewModel.setFlavour(dataset[0].flav_name,dataset[0].prev)
        }

        findNavController().navigate(R.id.action_fragment_main_to_fragment_flavours)
    }

    fun quantityInc() {
        sharedViewModel.quantityIncdEC(true)
    }

    fun quantityDec() {
        sharedViewModel.quantityIncdEC(false)
    }



}