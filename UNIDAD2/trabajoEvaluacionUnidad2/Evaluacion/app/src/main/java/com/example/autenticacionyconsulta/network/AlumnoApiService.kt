package com.example.autenticacionyconsulta.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

//MIS SOLICITUDES WEB
interface AlumnoApiService {
    //ealizar una solicitud SOAP para obtener acceso al sistema.
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/accesoLogin\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getAcceso(
        @Body requestBody: RequestBody
    ): ResponseBody

    // realizar una solicitud SOAP para obtener información principal del alumno.
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAlumnoAcademicoWithLineamiento\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getInfo(
        @Body requestBody: RequestBody
    ): ResponseBody

    //método para realizar una solicitud SOAP para obtener el kardex
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllKardexConPromedioByAlumno\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getKardex(
        @Body requestBody: RequestBody
    ): ResponseBody

    //método para realizar una solicitud SOAP para obtener el horario
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCargaAcademicaByAlumno\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getHorario(
        @Body requestBody: RequestBody
    ): ResponseBody

    //método para realizar una solicitud SOAP para obtener las calificaciones parciales
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCalifUnidadesByAlumno\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getParciales(
        @Body requestBody: RequestBody
    ): ResponseBody

    //método para realizar una solicitud SOAP para obtener las calificaciones finales
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllCalifFinalByAlumnos\""
    )

    @POST("ws/wsalumnos.asmx")
    suspend fun getFinales(
        @Body requestBody: RequestBody
    ): ResponseBody

    //método para realizar una solicitud HTTP GET para obtener cookies
    @GET("ws/wsalumnos.asmx")
    suspend fun getCokies(): ResponseBody
}