package com.example.autenticacionyconsulta.data

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex
import com.example.autenticacionyconsulta.network.AlumnoApiService
import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody


interface AlumnosRepository {
    // Método para obtener acceso a la aplicación con las credenciales proporcionadas.
    suspend fun getAccess(matricula: String, password: String, tipoUsuario:String):String
    // Método para obtener la información del alumno.
    suspend fun getInfo():String
    // Método para obtener el kardex del alumno.
    suspend fun getKardex(lineamiento: String):String
    // Método para obtener el horario del alumno.
    suspend fun getHorario():String
    // Método para obtener las calificaciones parciales del alumno.
    suspend fun getParciales():String
    // Método para obtener las calificaciones finales del alumno.
    suspend fun getFinales(modoEducativo:String):String
}
//Nuestra llama a la api de red
class NetworkAlumnosRepository(
    private val alumnoApiService: AlumnoApiService
): AlumnosRepository{
    // Obtener acceso  con las credenciales
    override suspend fun getAccess(matricula: String, password: String, tipoUsuario:String):String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${password}</strContrasenia>
                  <tipoUsuario>${tipoUsuario}</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        return try {
            alumnoApiService.getCokies()
            var credencial=alumnoApiService.getAcceso(requestBody).string().split("{","}")
            if(credencial.size>1){
               return "{"+credencial[1]+"}"
            }
            return  ""
        }catch (e:Exception){
            ""
        }
    }
    // Método para obtener la información del alumno.
    override suspend fun getInfo():String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        return try {
            val respuestaInfo=alumnoApiService.getInfo(requestBody).string().split("{","}")
            if(respuestaInfo.size>1){
                "{"+respuestaInfo[1]+"}"
            } else
                ""
        }catch (e:Exception){
            ""
        }
    }
    // Método para obtener el kardex del alumno.
    override suspend fun getKardex(lineamiento:String):String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAllKardexConPromedioByAlumno xmlns="http://tempuri.org/">
                  <aluLineamiento>${lineamiento}</aluLineamiento>
                </getAllKardexConPromedioByAlumno>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            return alumnoApiService.getKardex(requestBody).string()
        }catch (e:Exception){
           return ""
        }
    }
    // Método para obtener el horario del alumno.
    override suspend fun getHorario():String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getCargaAcademicaByAlumno xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            return "["+alumnoApiService.getHorario(requestBody).string().split("[\r\n  ","]").get(1)+"]"
        }catch (e:Exception){
            return ""
        }
    }
    // Método para obtener las calificaciones parciales del alumno.
    override suspend fun getParciales():String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getCalifUnidadesByAlumno xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            return "["+alumnoApiService.getParciales(requestBody).string().split("[\r\n  ","]").get(1)+"]"
        }catch (e:Exception){
            return ""
        }
    }
    // Método para obtener las calificaciones finales del alumno.
    override suspend fun getFinales(modoEducativo:String):String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAllCalifFinalByAlumnos xmlns="http://tempuri.org/">
                  <bytModEducativo>${modoEducativo}</bytModEducativo>
                </getAllCalifFinalByAlumnos>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            return "["+alumnoApiService.getFinales(requestBody).string().split("[\r\n  ","]").get(1)+"]"
        }catch (e:Exception){
            return ""
        }
    }
}




