package pe.idat.appinstituto.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstitutoCliente {

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:80/wserviceapp/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: InstitutoServicio by lazy {
        buildRetrofit().create(InstitutoServicio::class.java)
    }
}