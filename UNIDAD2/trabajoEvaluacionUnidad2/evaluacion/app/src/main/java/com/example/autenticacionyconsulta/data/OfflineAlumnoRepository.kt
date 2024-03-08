package com.example.autenticacionyconsulta.data

import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex

class OfflineAlumnoRepository(private val alumnoDAO: AlumnoDAO):AlumnosRepository {
    suspend  fun insertCredenciales(credencialesAlumno: CredencialesAlumno)=alumnoDAO.insertCredenciales(credencialesAlumno)
    suspend  fun insertFinales(finalesAlumno: FinalesAlumno)=alumnoDAO.insertFinales(finalesAlumno)
    suspend  fun insertHorario(horarioAlumno: HorarioAlumno)=alumnoDAO.insertHorario(horarioAlumno)
    suspend  fun insertInformacion(informacionAlumno: InformacionAlumno)=alumnoDAO.insertInformacionAlumno(informacionAlumno)
    suspend  fun insertKardex(kardexMateria: KardexMateria)=alumnoDAO.insertKardex(kardexMateria)
    suspend  fun insertParciales(parcialesAlumno: ParcialesAlumno)=alumnoDAO.insertParciales(parcialesAlumno)
    suspend  fun insertResumen(resumenKardex: ResumenKardex)=alumnoDAO.insertResumenKardex(resumenKardex)
    //--------------Obtener---------------------------------
    override suspend fun getAccess(matricula: String, password: String, tipoUsuario:String):String=alumnoDAO.getAccess(matricula,password,tipoUsuario)
    override suspend fun getInfo():String=alumnoDAO.getInfo()
    override suspend fun getKardex(lineamiento: String): String =alumnoDAO.getKardex()
    override suspend fun getHorario(): String=alumnoDAO.getHorario()
    override suspend fun getParciales(): String=alumnoDAO.getParciales()
    override suspend fun getFinales(modoEducativo: String): String =alumnoDAO.getFinales()
    suspend fun getResumenKardex():String = alumnoDAO.getResumenKardex()
    //-------------Eliminar---------------------------------
    suspend fun deleteCredenciales()=alumnoDAO.deleteCredenciales()
    suspend fun deleteFinales()=alumnoDAO.deleteFinales()
    suspend fun deleteHorario()=alumnoDAO.deleteHorario()
    suspend fun deleteKardex()=alumnoDAO.deleteKardex()
    suspend fun deleteParciales()=alumnoDAO.deleteParciales()
    suspend fun deleteResumenKardex()=alumnoDAO.deleteResumen()
}