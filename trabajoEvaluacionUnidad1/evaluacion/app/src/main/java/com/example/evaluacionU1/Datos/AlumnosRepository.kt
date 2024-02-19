package com.example.evaluacionU1.Datos

import com.example.evaluacionU1.modelos.CredencialesAlumno
import com.example.evaluacionU1.modelos.InformacionAlumno
import com.example.evaluacionU1.network.AlumnoApiService
import com.example.evaluacionU1.network.AlumnoInfoService
import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


interface AlumnosRepository {
    suspend fun getAccess(matricula: String, password: String):Boolean
    suspend fun getInfo():String
}
// Implementación de la interfaz AlumnosRepository que interactúa con servicios web.
class NetworkAlumnosRepository(
    private val alumnoApiService: AlumnoApiService,
    private val alumnoInfoService: AlumnoInfoService

): AlumnosRepository{
    // Función para verificar el acceso de un alumno.
    override suspend fun getAccess(matricula: String, password: String ):Boolean{
        // Construcción de un XML de solicitud para autenticar al alumno.
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${password}</strContrasenia>
                  <tipoUsuario>ALUMNO</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()


        // Convertir el XML de solicitud en un objeto RequestBody.
        val requestBody=xml.toRequestBody()
        // Realizar una solicitud para obtener las cookies de autenticación.
        alumnoApiService.getCokies()
        // Intentar realizar la solicitud de acceso al servicio web
        return try {
            // Realizar la solicitud de acceso y obtener la respuesta como cadena.
            var respuestDatos=alumnoApiService.getAcceso(requestBody).string().split("{","}")
            // Verificar si la respuesta contiene datos
            if(respuestDatos.size>1){
                val result = Gson().fromJson("{"+respuestDatos[1]+"}", CredencialesAlumno::class.java)
                result.acceso.equals("true")
            } else
                false
        }catch (e:IOException){
            false
        }
    }
    // Función para obtener información de un alumno.
    override suspend fun getInfo():String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        // Convertir el XML de solicitud en un objeto RequestBody.
        val requestBody=xml.toRequestBody()
        // Intentar realizar la solicitud de información al servicio web
        return try {
            // Realizar la solicitud de información y obtener la respuesta como cadena.
            val respuestaInfo=alumnoInfoService.getInfo(requestBody).string().split("{","}")
            // Verificar si la respuesta
            if(respuestaInfo.size>1){
                val result = Gson().fromJson("{"+respuestaInfo[1]+"}", InformacionAlumno::class.java)
                ""+result
            } else
                ""
        }catch (e:IOException){
            ""
        }
    }
}





