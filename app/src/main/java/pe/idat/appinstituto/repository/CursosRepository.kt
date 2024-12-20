package pe.idat.appinstituto.repository

import androidx.lifecycle.MutableLiveData
import pe.idat.appinstituto.retrofit.InstitutoCliente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class CursosRepository {
    fun getItems(): MutableLiveData<List<String>> {
        val data = MutableLiveData<List<String>>()
        InstitutoCliente.retrofitService.getItems().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("CursosRepository", "Error: ${t.message}")
            }
        })
        return data
    }
}