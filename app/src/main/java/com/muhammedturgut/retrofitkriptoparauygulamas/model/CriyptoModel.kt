package com.muhammedturgut.retrofitkriptoparauygulamas.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //Bir değer gelecek ismi bu buu al currncy değerine ata
   // @SerializedName("currency")
    val currency:String,
   // @SerializedName("price")
    val price:String
)