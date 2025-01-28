package com.muhammedturgut.retrofitkriptoparauygulamas.servics

import com.muhammedturgut.retrofitkriptoparauygulamas.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //@GET, @UPDATE , @POST, DELETE
    //https://raw.githubusercontent.com/
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CryptoModel>>
    //get data fonksiyonu çalıştığı zaman verileri adresten indirecek ve bir List olarak döndürecek.
}