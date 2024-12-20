package pe.idat.appinstituto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.idat.appinstituto.repository.AsistenciaRepository
import pe.idat.appinstituto.repository.ReservasRepository
import pe.idat.appinstituto.retrofit.response.ResponseAsistencia
import pe.idat.appinstituto.retrofit.response.ResponseCurso
import pe.idat.appinstituto.retrofit.response.ResponseReservas

class AsistenciaViewModel : ViewModel() {
    private var repository = AsistenciaRepository()
    var responseAsistencia: LiveData<ResponseAsistencia>

    init {
        responseAsistencia = repository.asistenciaResponse
    }

    fun listarAsistencia(idAlumno: String): LiveData<List<ResponseAsistencia>> {
        return repository.listarAsistenciaRepository(idAlumno)
    }
}