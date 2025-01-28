package com.muhammedturgut.retrofitkriptoparauygulamas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhammedturgut.retrofitkriptoparauygulamas.R
import com.muhammedturgut.retrofitkriptoparauygulamas.adapter.RecyclerVİewAdaptere
import com.muhammedturgut.retrofitkriptoparauygulamas.databinding.ActivityMainBinding
import com.muhammedturgut.retrofitkriptoparauygulamas.model.CryptoModel
import com.muhammedturgut.retrofitkriptoparauygulamas.servics.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerVİewAdaptere.Listener {
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel> ?=null
    private var recyclerViewAdapter:RecyclerVİewAdaptere ?=null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView
        val layoutManger:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManger
        loadData()
    }

    private fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service=retrofit.create(CryptoAPI::class.java)
        val call =service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
              if(response.isSuccessful){
                  response.body()?.let {
                      cryptoModels=ArrayList(it)

                      cryptoModels?.let {
                          recyclerViewAdapter=RecyclerVİewAdaptere(it, this@MainActivity)
                          binding.recyclerView.adapter=recyclerViewAdapter
                      }


                      /*for(cryptoModels:CryptoModel in cryptoModels!!){
                         println(cryptoModels.price)
                          println(cryptoModels.currency)
                      }*/
                  }
              }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                //Bu oluşan hatayı loglarda yazacak.
                t.printStackTrace()
            }
        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this@MainActivity,"Cliced: ${cryptoModel.currency}",Toast.LENGTH_SHORT).show()
    }
}