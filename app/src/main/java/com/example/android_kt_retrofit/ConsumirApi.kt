package com.example.android_kt_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ConsumirApi {
    @GET("autor")
    fun getTraer(): Call<Autor>
}