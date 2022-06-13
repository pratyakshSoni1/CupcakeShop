package com.smartphone_codes.cupcakeshop.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.smartphone_codes.cupcakeshop.dataSource.dataset
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {



// cupcake data properties with backing property

    private val SAME_DAY_PICKUP_PRICE = 10.00
    private val NEXT_DAY_PICKUP_PRICE = 5.00

    private val _quantity = MutableLiveData<Int>(1)
    val quantity:LiveData<Int> = _quantity

    private val _flavour = MutableLiveData<Int>(0)
    val flavour : LiveData<Int> = _flavour

    private val _flavourNameString=MutableLiveData<String>("")
    val flavourNameString:LiveData<String> = _flavourNameString

    private val _flavourPreview = MutableLiveData<Int>(0)
    val flavourPreview : LiveData<Int> = _flavourPreview

    private val _date = MutableLiveData<String>()
    val date:LiveData<String> = _date
    val dateOptions = getPickUpOptions()

    private val _price = MutableLiveData<Double>(0.0)
    val price:LiveData<Double> =_price

    private val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice:LiveData<Double> = _totalPrice

//cupcake properties setter methods

    fun setFlavour(desiredFlavour:Int,prevImgId:Int){
        _flavour.value = desiredFlavour
        _flavourPreview.value = prevImgId
        updatePrice()
    }

    fun setDate(pickupDate:String){
        _date.value = pickupDate
        updatePrice()
    }

    fun setPrice(price:Double){
        _price.value = price
    }

    fun resetOrder(){
        _date.value = dateOptions[0]
        _flavour.value = 0
        _quantity.value = 1
        _price.value = 0.00
    }

    fun setFlavourName(name:String){
        _flavourNameString.value = name
    }

    private fun updatePrice(){
        var calculatedPrice = (_quantity.value ?: 0) * _price.value!!

        if(_date.value == dateOptions[0]){
            calculatedPrice+=SAME_DAY_PICKUP_PRICE
        }else if(_date.value == dateOptions[1]){
            calculatedPrice+=NEXT_DAY_PICKUP_PRICE
        }

        _totalPrice.value = calculatedPrice

    }

    fun quantityIncdEC(INC:Boolean){
        var qty:Int? = _quantity.value?.toInt()
        if(INC){
           qty=qty!!+1
        }else{
            qty=qty!!-1
        }

        _quantity.value = if(qty<=0) _quantity.value else if(qty>=100) _quantity.value else qty

    }


    private fun getPickUpOptions():List<String>{
        //Get next 4 days list from today to next 4 for radio buttons
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat ("E MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()

        repeat(4){
            options.add(formatter.format(calender.time))
            calender.add(Calendar.DATE , 1)
        }
        return options
    }

     fun hasNoFlavourSet():Boolean{
         // verify if flavour is not already set
         return _flavour.value == 0
     }

    init{
        //reset order if viewModel instance is recreated or when created
        resetOrder()
    }

}