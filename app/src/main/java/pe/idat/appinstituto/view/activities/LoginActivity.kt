package pe.idat.appinstituto.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.idat.appinstituto.R
import pe.idat.appinstituto.utilitarios.AppMensaje
import pe.idat.appinstituto.utilitarios.TipoMensaje
import pe.idat.appinstituto.databinding.ActivityLoginBinding
import pe.idat.appinstituto.db.entity.AlumnoEntity
import pe.idat.appinstituto.viewmodel.AuthViewModel
import pe.idat.appinstituto.retrofit.response.ResponseLogin
import pe.idat.appinstituto.viewmodel.AlumnoViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(
            layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)

        alumnoViewModel = ViewModelProvider(this)
            .get(AlumnoViewModel::class.java)
        alumnoViewModel.eliminartodo()

        binding.btnlogin.setOnClickListener(this)
        authViewModel.responseLogin.observe(
            this, Observer {
                    response -> obtenerDatosLogin(response)
            }
        )

    }
    private fun obtenerDatosLogin(
        responseLogin: ResponseLogin) {
        if(responseLogin.rpta){

            val nuevoAlumno = AlumnoEntity(
                responseLogin.id_alumno,
                responseLogin.nombre,
                responseLogin.apellido,
                responseLogin.password)
            alumnoViewModel.insertar(nuevoAlumno)

            startActivity(Intent(applicationContext,
                MainActivity::class.java))
            AppMensaje.mensaje(binding.root,
                responseLogin.mensaje,
                TipoMensaje.CORRECTO)
            finish()

        }else{
            AppMensaje.mensaje(binding.root,
                responseLogin.mensaje,
                TipoMensaje.ERROR)
        }
        binding.btnlogin.isEnabled = true

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnlogin -> autenticarUsuario()
        }
    }

    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        if(validarUsuarioPassword()){
            authViewModel.autenticarUsuario(
                binding.etusuario.text.toString(),
                binding.etpassword.text.toString())
        }else{
            AppMensaje.mensaje(binding.root,
                "Ingrese usuario y/o password",
                TipoMensaje.ERROR)
            binding.btnlogin.isEnabled = true
        }

    }
    private fun validarUsuarioPassword():Boolean{
        var okValidacion = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        }else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }

}
