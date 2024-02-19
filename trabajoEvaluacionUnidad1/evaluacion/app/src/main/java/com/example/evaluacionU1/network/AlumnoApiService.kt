package com.example.evaluacionU1.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface AlumnoApiService {
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/accesoLogin\"",
        //"Cookie: .ASPXANONYMOUS=Ep4u2XmY2gEkAAAAOWQ1NzE0ZjMtNDBjZi00NjVmLWJjNDEtYmE3MTIwMmE3ZDgwq__VynMXe9_0bf2Sns0hO3CtLws1"
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getAcceso(
        @Body requestBody: RequestBody
    ): ResponseBody

    @GET("ws/wsalumnos.asmx")
    suspend fun getCokies(): ResponseBody
}