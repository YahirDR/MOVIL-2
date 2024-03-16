package com.example.autenticacionyconsulta.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex
import kotlinx.coroutines.flow.Flow
//DAO de la base de datos
@Dao
interface AlumnoDAO {
    //Setters
    @Insert
    suspend fun insertCredenciales(credencialesAlumno: CredencialesAlumno)
    @Insert
    suspend fun insertFinales(finalesAlumno: FinalesAlumno)
    @Insert
    suspend fun insertHorario(horarioAlumno: HorarioAlumno)
    @Insert
    suspend fun insertInformacionAlumno(informacionAlumno: InformacionAlumno)
    @Insert
    suspend fun insertKardex(kardexMateria: KardexMateria)
    @Insert
    suspend fun insertParciales(parcialesAlumno: ParcialesAlumno)
    @Insert
    suspend fun insertResumenKardex(resumenKardex: ResumenKardex)
    //Getters
   @Query("SELECT * from Credenciales where matricula=:mat")
   suspend fun getAccess(mat: String): CredencialesAlumno?
   @Query("SELECT COUNT(*) from Credenciales")
   suspend fun hayUsuario(): Int
   @Query("SELECT * from Finales")
   suspend fun getFinales(): MutableList<FinalesAlumno>?
   @Query("SELECT * from Horario")
   suspend fun getHorario(): MutableList<HorarioAlumno>?
   @Query("SELECT * from Informacion")
   suspend fun getInfo(): InformacionAlumno
   @Query("SELECT * from Kardex")
   suspend fun getKardex(): MutableList<KardexMateria>?
   @Query("SELECT * from Parciales")
   suspend fun getParciales(): MutableList<ParcialesAlumno>?
    @Query("SELECT * from ResumenKardex")
    suspend fun getResumenKardex(): ResumenKardex?
    //Eliminar
    @Query("delete from Credenciales")
    suspend fun deleteCredenciales()
    @Query("delete from Finales")
    suspend fun deleteFinales()
    @Query("delete from Horario")
    suspend fun deleteHorario()
    @Query("delete from Informacion")
    suspend fun deleteInformacion()
    @Query("delete from Kardex")
    suspend fun deleteKardex()
    @Query("delete from Parciales")
    suspend fun deleteParciales()
    @Query("delete from ResumenKardex")
    suspend fun deleteResumen()
}