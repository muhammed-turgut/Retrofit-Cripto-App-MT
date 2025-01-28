package com.muhammedturgut.retrofitkriptoparauygulamas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammedturgut.retrofitkriptoparauygulamas.R
import com.muhammedturgut.retrofitkriptoparauygulamas.databinding.RecyclerRowBinding
import com.muhammedturgut.retrofitkriptoparauygulamas.model.CryptoModel
import okhttp3.internal.http2.Http2Connection.Listener

class RecyclerVİewAdaptere(val recyclerViewList: ArrayList<CryptoModel>,val listener: Listener):RecyclerView.Adapter<RecyclerVİewAdaptere.RecyclerViewHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val color:Array<String> = arrayOf("#EFB036","#FF9D23","#16C47F","#4C7B8B","#2A004E","#23486A","#500073","#C62300","#F14A00","#3B6790")

    class RecyclerViewHolder(view:View):RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener:Listener){

            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }

            itemView.setBackgroundColor(Color.parseColor(colors[position % 10]))
            itemView.findViewById<TextView>(R.id.textName).text=cryptoModel.currency
            itemView.findViewById<TextView>(R.id.text_price).text=cryptoModel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
     return RecyclerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return recyclerViewList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
       holder.bind(recyclerViewList[position],color,position,listener)
    }
}