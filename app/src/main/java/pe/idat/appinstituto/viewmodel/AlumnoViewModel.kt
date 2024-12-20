package pe.idat.appinstituto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.idat.appinstituto.db.InstitutoRoomDatabase
import pe.idat.appinstituto.db.entity.AlumnoEntity
import pe.idat.appinstituto.repository.AlumnoRepository

class AlumnoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlumnoRepository
    init {
        val alumnoDao = InstitutoRoomDatabase
            .getDatabase(application).alumnoDao()
        repository = AlumnoRepository(alumnoDao)
    }

    fun insertar(alumnoEntity: AlumnoEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertar(alumnoEntity)
        }
    fun actualizar(alumnoEntity: AlumnoEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizar(alumnoEntity)
        }
    fun eliminartodo() = viewModelScope.launch(Dispatchers.IO) {
        repository.eliminartodo()
    }
    fun obtener() : LiveData<AlumnoEntity>{
        return repository.obtener()
    }
}