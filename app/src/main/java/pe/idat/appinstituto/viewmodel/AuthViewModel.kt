package pe.idat.appinstituto.viewmodel

import pe.idat.appinstituto.retrofit.request.RequestLogin
import pe.idat.appinstituto.retrofit.response.ResponseLogin
import pe.idat.appinstituto.repository.AuthRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel(){

    var responseLogin: LiveData<ResponseLogin>
    private var repository = AuthRepository()

    init {
        responseLogin = repository.loginResponse
    }

    fun autenticarUsuario(usuario: String,
                          password: String){
        responseLogin = repository.autenticarUsuario(
            RequestLogin(usuario, password))
    }
}