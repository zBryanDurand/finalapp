package pe.idat.appinstituto.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import pe.idat.appinstituto.retrofit.request.RequestJustificacion
import pe.idat.appinstituto.retrofit.request.RequestReservas
import pe.idat.appinstituto.retrofit.response.ResponseJustificacion
import pe.idat.appinstituto.retrofit.response.ResponseNotas
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JustificacionRepository {

    var responseJustificacion =
        MutableLiveData<List<ResponseJustificacion>>()
    var justificacionResponse = MutableLiveData<ResponseJustificacion>()


    fun registrarJustificacion(requestRegistro: RequestJustificacion)
            : MutableLiveData<ResponseJustificacion>{
        val call: Call<ResponseJustificacion> = InstitutoCliente
            .retrofitService.registroJustificacion(requestRegistro)
        call.enqueue(object : Callback<ResponseJustificacion>{
            override fun onResponse(p0: Call<ResponseJustificacion>, p1: Response<ResponseJustificacion>) {
                justificacionResponse.value = p1.body()
                Log.i("INFO-API", p1.body().toString())
            }
            override fun onFailure(p0: Call<ResponseJustificacion>, p1: Throwable) {
                Log.e("ErrorAPIRegistroJustificacion",
                    p1.message.toString())
            }
        })
        return justificacionResponse
    }

    fun listarJustificacionRepository(idAlumno: String): MutableLiveData<List<ResponseJustificacion>> {
        val call: Call<List<ResponseJustificacion>> = InstitutoCliente
            .retrofitService.listarJutificacion(idAlumno)
        call.enqueue(object : Callback<List<ResponseJustificacion>> {
            override fun onResponse(
                p0: Call<List<ResponseJustificacion>>,
                p1: Response<List<ResponseJustificacion>>
            ) {
                responseJustificacion.value = p1.body()
            }
            override fun onFailure(p0: Call<List<ResponseJustificacion>>, p1: Throwable) {
                Log.e("ErrorAPIJustificacion", p1.message.toString())
            }
        })
        return responseJustificacion
    }

}