package pe.idat.appinstituto.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import pe.idat.appinstituto.retrofit.response.ResponseAsistencia
import pe.idat.appinstituto.retrofit.response.ResponseCurso
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AsistenciaRepository {
    var responseAsistencia = MutableLiveData<List<ResponseAsistencia>>()
    var responseCurso = MutableLiveData<List<ResponseCurso>>()
    var asistenciaResponse = MutableLiveData<ResponseAsistencia>()


    fun listarAsistenciaRepository(idAlumno: String): MutableLiveData<List<ResponseAsistencia>> {
        val call: Call<List<ResponseAsistencia>> = InstitutoCliente
            .retrofitService.listarAsistencia(idAlumno)
        call.enqueue(object : Callback<List<ResponseAsistencia>> {
            override fun onResponse(
                p0: Call<List<ResponseAsistencia>>,
                p1: Response<List<ResponseAsistencia>>
            ) {
                responseAsistencia.value = p1.body()
            }

            override fun onFailure(p0: Call<List<ResponseAsistencia>>, p1: Throwable) {
                Log.e("ErrorAPIAsistencia", p1.message.toString())
            }
        })
        return responseAsistencia
    }
}