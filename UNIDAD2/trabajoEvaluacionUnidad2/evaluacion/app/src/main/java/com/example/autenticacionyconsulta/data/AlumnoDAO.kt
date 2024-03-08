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

@Dao
interface AlumnoDAO {
    //setters
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
    //GETTERS
   @Query("SELECT * from Credenciales where (matricula=:mat and contrasenia=:pass and tipoUsuario=:tip)")
   fun getAccess(mat: String,pass:String,tip:String): String
   @Query("SELECT * from Finales")
   fun getFinales(): String
   @Query("SELECT * from Horario")
   fun getHorario(): String
   @Query("SELECT * from Informacion")
   fun getInfo(): String
   @Query("SELECT * from Kardex")
   fun getKardex(): String
   @Query("SELECT * from Parciales")
   fun getParciales(): String
    @Query("SELECT * from ResumenKardex")
    fun getResumenKardex(): String
    //DELETE
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