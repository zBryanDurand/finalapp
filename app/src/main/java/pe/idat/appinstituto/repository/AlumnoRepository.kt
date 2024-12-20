package pe.idat.appinstituto.repository

import androidx.lifecycle.LiveData
import pe.idat.appinstituto.db.dao.AlumnoDao
import pe.idat.appinstituto.db.entity.AlumnoEntity

class AlumnoRepository (private val alumnoDao: AlumnoDao) {

    suspend fun insertar(alumnoEntity: AlumnoEntity){
        alumnoDao.insertar(alumnoEntity)
    }
    suspend fun actualizar(alumnoEntity: AlumnoEntity){
        alumnoDao.actualizar(alumnoEntity)
    }
    suspend fun eliminartodo(){
        alumnoDao.eliminarTodo()
    }
    fun obtener(): LiveData<AlumnoEntity>{
        return alumnoDao.obtener()
    }

}