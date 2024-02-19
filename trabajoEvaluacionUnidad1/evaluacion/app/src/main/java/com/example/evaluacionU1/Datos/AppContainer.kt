package com.example.evaluacionU1.Datos

import com.example.evaluacionU1.network.AlumnoApiService
import com.example.evaluacionU1.network.AlumnoInfoService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

interface AppContainer{
    val alumnosRepository:AlumnosRepository
}
// Implementación predeterminada de la interfaz AppContainer.
class DefaultAppContainer : AppContainer {
    // URL base para las solicitudes HTTP.
    private val BASE_URL =
        "https://sicenet.surguanajuato.tecnm.mx/"
    // Interceptor para manejar las cookies en las solicitudes HTTP.
    private val interceptor= CookiesInterceptor()
    // Cliente HTTP OkHttp para realizar solicitudes.
    private val cliente = OkHttpClient.Builder().addInterceptor(interceptor).build()
    // USO DE Retrofit para realizar solicitudes HTTP y convertir las respuestas a objetos Kotlin.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL).client(cliente)
        .build()
    // Servicio Retrofit para realizar solicitudes relacionadas con los datos alumnos.
    private val retrofitService : AlumnoApiService by lazy {
        retrofit.create(AlumnoApiService::class.java)
    }
    // Servicio Retrofit para obtener información del alumno.
    private val retrofitServiceInfo : AlumnoInfoService by lazy {
        retrofit.create(AlumnoInfoService::class.java)
    }
    // Repositorio de alumnos que utiliza los servicios Retrofit.
    override val alumnosRepository: AlumnosRepository by lazy {
        NetworkAlumnosRepository(retrofitService,retrofitServiceInfo)
    }
}
// Interceptor para manejar las cookies en las solicitudes HTTP.
class CookiesInterceptor : Interceptor {

    // Variable que almacena las cookies
    private var cookies: List<String> = emptyList()

    // Método para establecer las cookies
    fun setCookies(cookies: List<String>) {
        this.cookies = cookies
    }
    // Método para interceptar y modificar las solicitudes HTTP.
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Agregar las cookies al encabezado de la solicitud
        if (cookies.isNotEmpty()) {
            val cookiesHeader = StringBuilder()
            for (cookie in cookies) {
                if (cookiesHeader.isNotEmpty()) {
                    cookiesHeader.append("; ")
                }
                cookiesHeader.append(cookie)
            }

            request = request.newBuilder()
                .header("Cookie", cookiesHeader.toString())
                .build()
        }

        val response = chain.proceed(request)

        // Almacenar las cookies
        val receivedCookies = response.headers("Set-Cookie")
        if (receivedCookies.isNotEmpty()) {
            setCookies(receivedCookies)
        }

        return response
    }
}