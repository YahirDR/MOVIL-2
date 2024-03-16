package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Horario")
data class HorarioAlumno(
    var CreditosMateria: String="",
    var Docente: String="",
    var EstadoMateria: String="",
    var Grupo: String="",
    var Jueves: String="",
    var Lunes: String="",
    var Martes: String="",
    var Materia: String="",
    var Miercoles: String="",
    var Observaciones: String="",
    var Sabado: String="",
    var Semipresencial: String="",
    var Viernes: String="",
    @PrimaryKey
    var clvOficial: String=""
)