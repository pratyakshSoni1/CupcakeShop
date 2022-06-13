package com.smartphone_codes.cupcakeshop.mainActivityFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartphone_codes.cupcakeshop.R
import com.smartphone_codes.cupcakeshop.adapters.FlavourAdapter
import com.smartphone_codes.cupcakeshop.dataSource.dataset
import com.smartphone_codes.cupcakeshop.databinding.FragmentFlavoursBinding
import com.smartphone_codes.cupcakeshop.model.OrderViewModel

class FragmentFlavours : Fragment() {

    private var binding : FragmentFlavoursBinding? = null
    private val sharedViewModel : OrderViewModel by activityViewModels()
    private var currentItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavoursBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {

            flavourFragment = this@FragmentFlavours
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel

            flavourRecview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

            flavourRecview.adapter=FlavourAdapter(requireContext(),dataset)

        }

        dataset.forEach{
            //restore dataFrom viewModel when fragment is inflated
            if(it.flav_name == sharedViewModel.flavour.value){
                currentItem = dataset.indexOf(it)
                flavourDataRestore(currentItem)
                Log.d("deb"," Scrolled to $currentItem")

            } }

        /* This is the important part i have learned/implemented the onTouch listeners here it tells the direction of touch
        * and recycler view acts based on directiond !
        *
        * Warning : warning shown here is for if touch listener is on command so onClicklesteners will not
        * work in most cases so we use to call performClick() inside touchListener which perform action written inside
        * clickListenres()*/

        binding?.flavourRecview?.setOnTouchListener(object:View.OnTouchListener{

            //onTouch listener to get swipe directions and changeflavour

            var downX = 0f
            var upX = 0f

            override fun onTouch(viewTouched: View?, mEvent: MotionEvent?): Boolean {
                if (mEvent?.action == MotionEvent.ACTION_DOWN) {
                    downX = mEvent.x
                }

                when (mEvent?.action) {

                    MotionEvent.ACTION_DOWN -> { downX = mEvent.x }

                    MotionEvent.ACTION_UP -> {
                        upX = mEvent.x

                        Log.d("deb"," down : $downX up : $upX")
                        setNewFlavour(changeFlavour(upX>downX))

                    }
                }

                return true

            }
        })



    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    fun flavourDataRestore(destinationPosition: Int){

        //smoothScrolltoPosition() was not working so scrollToPositon() for restoring data from viewModel

        binding?.apply{
            flavourRecview.scrollToPosition(destinationPosition)
            sharedViewModel.setPrice(dataset[destinationPosition].flavCost)
            sharedViewModel.setFlavour(dataset[destinationPosition].flav_name,dataset[destinationPosition].prev)
            sharedViewModel.setFlavourName(getString(dataset[destinationPosition].flav_name))
        }
    }

    fun setNewFlavour(destinationPosition:Int){

        //set flavour to given position with smooth scroll

        binding?.apply{
            flavourRecview.smoothScrollToPosition(destinationPosition)
            sharedViewModel.setFlavourName(getString(dataset[destinationPosition].flav_name))
            sharedViewModel.setPrice(dataset[destinationPosition].flavCost)
            sharedViewModel.setFlavour(dataset[destinationPosition].flav_name,dataset[destinationPosition].prev)
        }

    }

    fun changeFlavour(SWIPE_RIGHT:Boolean):Int{

        //To change flavour according to swipe gestures Direction

        if(SWIPE_RIGHT){
            currentItem--
        }else{
            currentItem++
        }

        currentItem = if (currentItem<0) 0 else if(currentItem>=dataset.size) dataset.size -1 else currentItem
        return currentItem

    }

    fun goToNextScreen(){
        //Opens pickup fragment
        findNavController().navigate(R.id.action_fragment_flavours_to_fragment_time)
    }

    fun cancelOrder() {
        Toast.makeText(activity, "Cancel Order", Toast.LENGTH_SHORT).show()
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_fragment_flavours_to_fragment_main)
    }

}