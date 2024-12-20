package pe.idat.appinstituto.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.idat.appinstituto.repository.CursosRepository

class CursosViewModel : ViewModel() {
    private val cursosRepository = CursosRepository()
    val items: MutableLiveData<List<String>> = cursosRepository.getItems()
}