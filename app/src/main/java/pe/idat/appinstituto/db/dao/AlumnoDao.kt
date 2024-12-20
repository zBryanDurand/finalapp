package pe.idat.appinstituto.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.idat.appinstituto.db.entity.AlumnoEntity

@Dao
interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg alumno: AlumnoEntity)

    @Update
    fun actualizar(vararg alumno: AlumnoEntity)

    @Query("DELETE FROM alumno")
    fun eliminarTodo()

    @Query("SELECT * FROM alumno LIMIT 1")
    fun obtener(): LiveData<AlumnoEntity>
}