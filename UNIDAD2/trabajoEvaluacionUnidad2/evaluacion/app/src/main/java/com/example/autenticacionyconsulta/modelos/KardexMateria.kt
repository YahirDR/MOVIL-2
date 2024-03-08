package com.example.autenticacionyconsulta.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kardex")
data class KardexMateria(
    var A1: String="",
    var A2: Any?=null,
    var A3: Any?=null,
    var Acred: String="",
    var Calif: Int=0,
    var Cdts: Int=0,
    @PrimaryKey
    var ClvMat: String="",
    var ClvOfiMat: String="",
    var Materia: String="",
    var P1: String="",
    var P2: Any?=null,
    var P3: Any?=null,
    var S1: String="",
    var S2: Any?=null,
    var S3: Any?=null,
)