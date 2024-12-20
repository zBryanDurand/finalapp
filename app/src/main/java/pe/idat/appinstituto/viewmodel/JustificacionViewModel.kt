package pe.idat.appinstituto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.idat.appinstituto.repository.JustificacionRepository
import pe.idat.appinstituto.repository.ReservasRepository
import pe.idat.appinstituto.retrofit.request.RequestJustificacion
import pe.idat.appinstituto.retrofit.request.RequestReservas
import pe.idat.appinstituto.retrofit.response.ResponseJustificacion
import pe.idat.appinstituto.retrofit.response.ResponseReservas

class JustificacionViewModel : ViewModel(){

    private var repository = JustificacionRepository()
    var responseJustificacion: LiveData<ResponseJustificacion>

    init {
        responseJustificacion = repository.justificacionResponse
    }

    fun listarJustificacion(idAlumno: String): LiveData<List<ResponseJustificacion>> {
        return repository.listarJustificacionRepository(idAlumno)
    }

    fun registroJustificacion(id_asistencia: String,
                        motivo: String){
        responseJustificacion = repository.registrarJustificacion(
            RequestJustificacion(id_asistencia,motivo)
        )
    }
}