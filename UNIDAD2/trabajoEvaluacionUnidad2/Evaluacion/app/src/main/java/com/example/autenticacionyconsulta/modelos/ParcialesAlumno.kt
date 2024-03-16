package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parciales")
data class ParcialesAlumno(
    var C1: String="",
    var C10: String="",
    var C11: String="",
    var C12: String="",
    var C13: String="",
    var C2: String="",
    var C3: String="",
    var C4: String="",
    var C5: String="",
    var C6: String="",
    var C7: String="",
    var C8: String="",
    var C9: String="",
    var Grupo: String="",
    @PrimaryKey
    var Materia: String="",
    var Observaciones: String="",
    var UnidadesActivas: String=""
)