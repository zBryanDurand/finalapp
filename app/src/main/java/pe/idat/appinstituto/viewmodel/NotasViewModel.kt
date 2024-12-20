package pe.idat.appinstituto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.idat.appinstituto.repository.NotasRepository
import pe.idat.appinstituto.retrofit.response.ResponseNotas

class NotasViewModel : ViewModel() {

    private var repository = NotasRepository()

    fun listarNotas(idAlumno: String): LiveData<List<ResponseNotas>> {
        return repository.listarNotasPorCursoRepository(idAlumno)
    }
}