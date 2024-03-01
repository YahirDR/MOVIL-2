package com.example.autenticacionyconsulta.modelos

data class CredencialesAlumno(
   var acceso:String,
   var estatus:String,
   var tipoUsuario:Int,
   var contrasenia:String,
   var matricula:String
)
