package com.example.android_kt_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_kt_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retroftiTraer = RetrofitClient.consumirApi.getTraer()

        retroftiTraer.enqueue(object : Callback<Autor> {
            override fun onResponse(call: Call<Autor>, response: Response<Autor>) {
                val autor = response.body()
                if (autor != null) {
                    val bookList = autor.books
                    val adapter = BookAdapter(bookList)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Autor>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consultar Api Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }
}