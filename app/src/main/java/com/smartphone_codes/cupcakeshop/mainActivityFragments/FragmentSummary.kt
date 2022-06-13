package com.smartphone_codes.cupcakeshop.mainActivityFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.smartphone_codes.cupcakeshop.R
import com.smartphone_codes.cupcakeshop.databinding.FragmentSummaryBinding
import com.smartphone_codes.cupcakeshop.model.OrderViewModel


class FragmentSummary : Fragment() {

    private lateinit var binding:FragmentSummaryBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_summary,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            fragmentSummary = this@FragmentSummary
            viewModel = sharedViewModel

        }
    }


    fun sendOrder() {
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcake_quantites, sharedViewModel.quantity.value!!,sharedViewModel.quantity.value!!),
            getString(sharedViewModel.flavour.value!!),
            sharedViewModel.date.value.toString(),
            sharedViewModel.totalPrice.value.toString()
        )
        val address:Array<String> = arrayOf("cupcakeshop@gmail.com")

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_EMAIL,address)
            .putExtra(Intent.EXTRA_SUBJECT,getString(R.string.cupcake_order))
            .putExtra(Intent.EXTRA_TEXT,orderSummary)

        if(activity?.packageManager?.resolveActivity(intent,0) != null){
            startActivity(intent)
        }
    }

    fun cancelOrder() {
        Toast.makeText(activity, "Cancel Order", Toast.LENGTH_SHORT).show()
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_fragment_summary_to_fragment_main)
    }

}
