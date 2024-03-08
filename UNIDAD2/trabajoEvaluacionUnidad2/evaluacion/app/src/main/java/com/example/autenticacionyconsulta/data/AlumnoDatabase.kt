package com.example.autenticacionyconsulta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex

@Database(entities = [CredencialesAlumno::class, FinalesAlumno::class, HorarioAlumno::class,
    InformacionAlumno::class, KardexMateria::class, ParcialesAlumno::class, ResumenKardex::class],
    version = 1, exportSchema = false)
abstract class AlumnoDatabase : RoomDatabase(){
    abstract fun alumnoDAO(): AlumnoDAO

    companion object {
        @Volatile
        private var Instance: AlumnoDatabase? = null

        fun getDatabase(context: Context): AlumnoDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AlumnoDatabase::class.java, "notes_database")
                    .build().also { Instance = it }
            }
        }
    }
}
