package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Finales")
data class FinalesAlumno(
    var Observaciones: String="",
    var acred: String="",
    var calif: Int=0,
    var grupo: String="",
    @PrimaryKey
    var materia: String=""
)