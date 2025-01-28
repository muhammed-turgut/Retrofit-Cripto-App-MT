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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerVİewAdaptere.Listener {
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel> ?=null
    private var recyclerViewAdapter:RecyclerVİewAdaptere ?=null
    private lateinit var binding: ActivityMainBinding

    //Disposable (Kullan at)

    private var compositeDisposable:CompositeDisposable ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compositeDisposable=CompositeDisposable()
        //RecyclerView
        val layoutManger:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManger
        loadData()
    }

    private fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

            compositeDisposable?.add(retrofit.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handlerRespons))




       /*
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


                      for(cryptoModels:CryptoModel in cryptoModels!!){
                         println(cryptoModels.price)
                          println(cryptoModels.currency)
                      }
                  }
              }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                //Bu oluşan hatayı loglarda yazacak.
                t.printStackTrace()
            }
        })
        */

    }
    private fun handlerRespons(cryptoList: List<CryptoModel>){
        cryptoModels=ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter=RecyclerVİewAdaptere(it, this@MainActivity)
            binding.recyclerView.adapter=recyclerViewAdapter
        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this@MainActivity,"Cliced: ${cryptoModel.currency}",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        //Bu activite sonlanınca yapılacakları yapıyor.
        super.onDestroy()
        //
        compositeDisposable?.clear()
    }
}