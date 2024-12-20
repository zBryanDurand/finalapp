package pe.idat.appinstituto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.idat.appinstituto.repository.ReservasRepository
import pe.idat.appinstituto.retrofit.request.RequestReservas
import pe.idat.appinstituto.retrofit.response.ResponseReservas

class ReservasViewModel : ViewModel(){

    private var repository = ReservasRepository()
    var responseReservas: LiveData<ResponseReservas>

    init {
        responseReservas = repository.reservarResponse
    }

    fun listarReservas(idAlumno: String): LiveData<List<ResponseReservas>> {
        return repository.listarReservasRepository(idAlumno)
    }

    fun registroUsuario(id_alumno: String,
                        id_aula: String,
                        fecha_reserva: String,
                        hora_inicio: String,
                        hora_fin: String){
        responseReservas = repository.registrarReserva(
            RequestReservas(id_alumno,id_aula, fecha_reserva, hora_inicio, hora_fin)
        )
    }
}