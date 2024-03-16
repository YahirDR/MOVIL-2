package com.example.autenticacionyconsulta.data

import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex
//Metodos para la base de datos
interface AlumnosRepositoryDB{
    // Verificar si hay usuarios en la base de datos
    suspend fun hayUsuario():Int
    suspend fun getAccessDB(matricula: String):CredencialesAlumno?
    //obtener información de los alumnos desde la base
    suspend fun getInfoDB():InformacionAlumno
    suspend fun getKardexDB(lineamiento: String):MutableList<KardexMateria>?
    suspend fun getResumenKardexDB(): ResumenKardex?
    suspend fun getHorarioDB():MutableList<HorarioAlumno>?
    suspend fun getParcialesDB():MutableList<ParcialesAlumno>?
    suspend fun getFinalesDB():MutableList<FinalesAlumno>?
    // Métodos para insertar datos en la base
    suspend  fun insertCredenciales(credencialesAlumno: CredencialesAlumno)
    suspend  fun insertFinal(finalesAlumno: FinalesAlumno)
    suspend  fun insertHorario(horarioAlumno: HorarioAlumno)
    suspend  fun insertInformacion(informacionAlumno: InformacionAlumno)
    suspend  fun insertKardex(kardexMateria: KardexMateria)
    suspend  fun insertResumenKardex(resumenKardex: ResumenKardex)
    suspend  fun insertParciales(parcialesAlumno: ParcialesAlumno)
    suspend  fun insertResumen(resumenKardex: ResumenKardex)
    // Métodos para eliminar datos en base
    suspend fun deleteCredenciales()
    suspend fun deleteFinales()
    suspend fun deleteHorario()
    suspend fun deleteKardex()
    suspend fun deleteParciales()
    suspend fun deleteResumenKardex()
    suspend fun deleteInformacion()
}
// Implementación del repositorio para agregar, sacar y eliminar datos de la base de datos
class OfflineAlumnoRepository(private val alumnoDAO: AlumnoDAO):AlumnosRepositoryDB {
    // Insertar datos
    override suspend  fun insertCredenciales(credencialesAlumno: CredencialesAlumno)=alumnoDAO.insertCredenciales(credencialesAlumno)
     override suspend  fun insertFinal(finalesAlumno: FinalesAlumno)=alumnoDAO.insertFinales(finalesAlumno)
     override suspend  fun insertHorario(horarioAlumno: HorarioAlumno)=alumnoDAO.insertHorario(horarioAlumno)
     override suspend  fun insertInformacion(informacionAlumno: InformacionAlumno)=alumnoDAO.insertInformacionAlumno(informacionAlumno)
     override suspend  fun insertKardex(kardexMateria: KardexMateria)=alumnoDAO.insertKardex(kardexMateria)
     override suspend  fun insertResumenKardex(resumenKardex: ResumenKardex)=alumnoDAO.insertResumenKardex(resumenKardex)
     override suspend  fun insertParciales(parcialesAlumno: ParcialesAlumno)=alumnoDAO.insertParciales(parcialesAlumno)
     override suspend  fun insertResumen(resumenKardex: ResumenKardex)=alumnoDAO.insertResumenKardex(resumenKardex)
    //Obtener datos
    override suspend fun hayUsuario():Int=alumnoDAO.hayUsuario()
    override suspend fun getAccessDB(matricula: String):CredencialesAlumno?=alumnoDAO.getAccess(matricula)
     override suspend fun getInfoDB():InformacionAlumno=alumnoDAO.getInfo()
     override suspend fun getKardexDB(lineamiento: String): MutableList<KardexMateria>? =alumnoDAO.getKardex()
     override suspend fun getHorarioDB(): MutableList<HorarioAlumno>? =alumnoDAO.getHorario()
     override suspend fun getParcialesDB(): MutableList<ParcialesAlumno>? =alumnoDAO.getParciales()
     override suspend fun getFinalesDB(): MutableList<FinalesAlumno>? =alumnoDAO.getFinales()
     override suspend fun getResumenKardexDB():ResumenKardex? = alumnoDAO.getResumenKardex()
    //Eliminar datos
     override suspend fun deleteCredenciales()=alumnoDAO.deleteCredenciales()
     override suspend fun deleteFinales()=alumnoDAO.deleteFinales()
     override suspend fun deleteHorario()=alumnoDAO.deleteHorario()
     override suspend fun deleteKardex()=alumnoDAO.deleteKardex()
     override suspend fun deleteParciales()=alumnoDAO.deleteParciales()
     override suspend fun deleteResumenKardex()=alumnoDAO.deleteResumen()
     override suspend fun deleteInformacion()=alumnoDAO.deleteInformacion()
}