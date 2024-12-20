package pe.idat.appinstituto.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import pe.idat.appinstituto.retrofit.request.RequestReservas
import pe.idat.appinstituto.retrofit.response.ResponseNotas
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservasRepository {

    var responseReservas =
        MutableLiveData<List<ResponseReservas>>()
    var reservarResponse = MutableLiveData<ResponseReservas>()



    fun listarReservasRepository(idAlumno: String): MutableLiveData<List<ResponseReservas>> {
        val call: Call<List<ResponseReservas>> = InstitutoCliente
            .retrofitService.listarReservasService(idAlumno)
        call.enqueue(object : Callback<List<ResponseReservas>> {
            override fun onResponse(
                p0: Call<List<ResponseReservas>>,
                p1: Response<List<ResponseReservas>>
            ) {
                responseReservas.value = p1.body()
            }

            override fun onFailure(p0: Call<List<ResponseReservas>>, p1: Throwable) {
                Log.e("ErrorAPIReserva", p1.message.toString())
            }
        })
        return responseReservas
    }

    fun registrarReserva(requestRegistro: RequestReservas)
            : MutableLiveData<ResponseReservas>{
        val call: Call<ResponseReservas> = InstitutoCliente
            .retrofitService.registroReservar(requestRegistro)
        call.enqueue(object : Callback<ResponseReservas>{
            override fun onResponse(p0: Call<ResponseReservas>, p1: Response<ResponseReservas>) {
                reservarResponse.value = p1.body()
                Log.i("INFO-API", p1.body().toString())
            }
            override fun onFailure(p0: Call<ResponseReservas>, p1: Throwable) {
                Log.e("ErrorAPIRegistroReserva",
                    p1.message.toString())
            }
        })
        return reservarResponse
    }
}