package com.example.autenticacionyconsulta.data

import android.content.Context
import com.example.autenticacionyconsulta.network.AlumnoApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

interface AppContainer{
    // Repositorio para interactuar con los datos de los alumnos.
    val alumnosRepository:AlumnosRepository
    // Repositorio para interactuar con los datos de los alumnos almacenados en la base de datos.
    val alumnosRepositoryDB:AlumnosRepositoryDB
    // Repositorio para gestionar los  Workers .
    val workerRepository:WorkerRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    // URL base para las llamadas a la API.
    private val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx/"
    // manejar las cookies en las solicitudes HTTP.
    private val interceptor= CookiesInterceptor()
    // Cliente realizar llamadas a la API.
    private val cliente = OkHttpClient.Builder().addInterceptor(interceptor).build()
    // Instancia Retrofit para realizar llamadas a la API y convertir las respuestas en objetos.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL).client(cliente)
        .build()

    private val retrofitService : AlumnoApiService by lazy {
        retrofit.create(AlumnoApiService::class.java)
    }
    // interactuar con los datos de los alumnos almacenados localmente.
    override val alumnosRepositoryDB: AlumnosRepositoryDB by lazy {
        OfflineAlumnoRepository(AlumnoDatabase.getDatabase(context).alumnoDAO())
    }
    // Interactuar con los  Workers en la aplicación.
    override val workerRepository: WorkerRepository by lazy {
        WorkerManagement(context)
    }
    // interactuar con los datos  a través de la red.
    override val alumnosRepository: AlumnosRepository by lazy {
        NetworkAlumnosRepository(retrofitService)
    }
}
//Manejar las cookies en las solicitudes HTTP.
class CookiesInterceptor : Interceptor {

    // Variable para almacenar las cookies de manera persistente
    private val cookieStore = mutableMapOf<String, String>()

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Agregar las cookies al encabezado de la solicitud
        val cookiesHeader = StringBuilder()
        for ((name, value) in cookieStore) {
            if (cookiesHeader.isNotEmpty()) {
                cookiesHeader.append("; ")
            }
            cookiesHeader.append("$name=$value")
        }

        if (cookiesHeader.isNotEmpty()) {
            request = request.newBuilder()
                .header("Cookie", cookiesHeader.toString())
                .build()
        }

        val response = chain.proceed(request)

        // Almacenar las cookies de la respuesta para futuras solicitudes
        val receivedCookies = response.headers("Set-Cookie")
        for (cookie in receivedCookies) {
            val parts = cookie.split(";")[0].split("=")
            if (parts.size == 2) {
                val name = parts[0]
                val value = parts[1]
                cookieStore[name] = value
            }
        }

        return response
    }
}
