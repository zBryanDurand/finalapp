package pe.idat.appinstituto.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import pe.idat.appinstituto.retrofit.response.ResponseNotas
import pe.idat.appinstituto.viewmodel.AlumnoViewModel
import pe.idat.appinstituto.viewmodel.NotasViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotasRepository {

    var responseNotas = MutableLiveData<List<ResponseNotas>>()
    fun listarNotasPorCursoRepository(idAlumno: String): LiveData<List<ResponseNotas>> {
        val call: Call<List<ResponseNotas>> = InstitutoCliente
            .retrofitService.listarNotasPorCursoService(idAlumno)
        call.enqueue(object : Callback<List<ResponseNotas>> {
            override fun onResponse(
                p0: Call<List<ResponseNotas>>,
                p1: Response<List<ResponseNotas>>
            ) {
                responseNotas.value = p1.body()
            }

            override fun onFailure(p0: Call<List<ResponseNotas>>, p1: Throwable) {
                Log.e("ErrorAPI_Idat", p1.message.toString())
            }
        })
        return responseNotas
    }
}