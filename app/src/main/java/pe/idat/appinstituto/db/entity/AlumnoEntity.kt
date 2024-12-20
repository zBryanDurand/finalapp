package pe.idat.appinstituto.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno")
data class AlumnoEntity(
    @PrimaryKey
    val id_alumno: String,
    val nombre: String,
    val apellido: String,
    val password: String
)
