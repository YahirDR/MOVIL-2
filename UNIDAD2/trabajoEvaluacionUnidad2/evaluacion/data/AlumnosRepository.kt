package com.example.autenticacionyconsulta.data

import android.util.Log
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.network.AlumnoApiService
import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody


interface AlumnosRepository {
    suspend fun getAccess(matricula: String, password: String, tipoUsuario:String):Boolean
    suspend fun getInfo():String
    suspend fun getKardex(lineamiento: String):String
    suspend fun getHorario():String
    suspend fun getParciales():String
    suspend fun getFinales(modoEducativo:String):String
}

class NetworkAlumnosRepository(
    private val alumnoApiService: AlumnoApiService
): AlumnosRepository{
    override suspend fun getAccess(matricula: String, password: String, tipoUsuario:String):Boolean{
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
            var respuestDatos=alumnoApiService.getAcceso(requestBody).string().split("{","}")
            if(respuestDatos.size>1){
                val result = Gson().fromJson("{"+respuestDatos[1]+"}", CredencialesAlumno::class.java)
                result.acceso.equals("true")
            } else
                false
        }catch (e:Exception){
            false
        }
    }

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
                val result = Gson().fromJson("{"+respuestaInfo[1]+"}", InformacionAlumno::class.java)
                ""+result
            } else
                ""
        }catch (e:Exception){
            ""
        }
    }

    //-------------------KARDEX------------------------------
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
            var result=alumnoApiService.getKardex(requestBody).string()
            //val respuestaInfo=alumnoKardexService.getKardex(requestBody).string().split("{","}")
             return "jala"
        }catch (e:Exception){
           return "no jala"
        }
    }

    //-------------------HORARIO------------------------------
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
            var horario=alumnoApiService.getHorario(requestBody).string().split("{","}, ")
            Log.d("horari",horario.toString())
            return "jala"
        }catch (e:Exception){
            return "no jala"
        }
    }

    //-------------------PARCIALES------------------------------
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
            var result=alumnoApiService.getParciales(requestBody).string()
            //val respuestaInfo=alumnoKardexService.getKardex(requestBody).string().split("{","}")
            return "jala"
        }catch (e:Exception){
            return "no jala"
        }
    }

    //-------------------FINALES------------------------------
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
            var result=alumnoApiService.getFinales(requestBody).string()
            //val respuestaInfo=alumnoKardexService.getKardex(requestBody).string().split("{","}")
            return "jala"
        }catch (e:Exception){
            return "no jala"
        }
    }
}





