package com.smartphone_codes.cupcakeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.smartphone_codes.cupcakeshop.R
import com.smartphone_codes.cupcakeshop.dataSource.Flavours

class FlavourAdapter (val context: Context, val dataset : List<Flavours>) : RecyclerView.Adapter<FlavourAdapter.FlavourViewHolder>(){

    inner class FlavourViewHolder(view: View):RecyclerView.ViewHolder(view){
        val flavPreview:ImageView = view.findViewById(R.id.flav_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavourViewHolder {
       val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_flavours,parent,false)
        return FlavourViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FlavourViewHolder, position: Int) {
        holder.flavPreview.setImageResource(dataset[position].prev)

    }

    override fun getItemCount(): Int {
        return  dataset.size
    }

}