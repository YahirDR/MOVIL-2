package com.example.autenticacionyconsulta.modelos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kardex")
data class KardexMateria(
    var A1: String="",
    var A2: String="",
    var A3: String="",
    var Acred: String="",
    var Calif: Int=0,
    var Cdts: Int=0,
    @PrimaryKey
    var ClvMat: String="",
    var ClvOfiMat: String="",
    var Materia: String="",
    var P1: String="",
    var P2: String="",
    var P3: String="",
    var S1: String="",
    var S2: String="",
    var S3: String="",
)