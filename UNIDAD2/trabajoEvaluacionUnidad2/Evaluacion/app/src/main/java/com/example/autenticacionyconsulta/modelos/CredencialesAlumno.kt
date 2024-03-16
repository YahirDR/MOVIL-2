package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Credenciales")
data class CredencialesAlumno(
   var acceso:String="",
   var estatus:String="",
   var tipoUsuario:Int=0,
   var contrasenia:String="",
   @PrimaryKey
   var matricula:String=""
)
