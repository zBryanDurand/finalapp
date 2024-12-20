package pe.idat.appinstituto.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import pe.idat.appinstituto.retrofit.request.RequestLogin
import pe.idat.appinstituto.retrofit.response.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse = MutableLiveData<ResponseLogin>()

    fun autenticarUsuario(requestLogin: RequestLogin)
            : MutableLiveData<ResponseLogin>{
        val call: Call<ResponseLogin> = InstitutoCliente
            .retrofitService.login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(p0: Call<ResponseLogin>, p1: Response<ResponseLogin>) {
                loginResponse.value = p1.body()
            }
            override fun onFailure(p0: Call<ResponseLogin>, p1: Throwable) {
                Log.e("ErrorAPILogin",
                    p1.message.toString())
            }
        })
        return loginResponse
    }

}