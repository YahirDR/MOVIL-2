package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parciales")
data class ParcialesAlumno(
    var C1: String="",
    var C10: Any?=null,
    var C11: Any?=null,
    var C12: Any?=null,
    var C13: Any?=null,
    var C2: Any?=null,
    var C3: Any?=null,
    var C4: Any?=null,
    val C5: Any?=null,
    var C6: Any?=null,
    var C7: Any?=null,
    var C8: Any?=null,
    var C9: Any?=null,
    var Grupo: String="",
    @PrimaryKey
    var Materia: String="",
    var Observaciones: String="",
    var UnidadesActivas: String=""
)