package pe.idat.appinstituto.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.appinstituto.R
import pe.idat.appinstituto.R.id.codigoAsistencia
import pe.idat.appinstituto.databinding.FragmentJustificacionBinding
import pe.idat.appinstituto.databinding.FragmentReservaBinding
import pe.idat.appinstituto.retrofit.response.ResponseJustificacion
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import pe.idat.appinstituto.utilitarios.AppMensaje
import pe.idat.appinstituto.utilitarios.TipoMensaje
import pe.idat.appinstituto.view.adapter.JustificacionAdapter
import pe.idat.appinstituto.view.adapter.ReservasAdapter
import pe.idat.appinstituto.viewmodel.AlumnoViewModel
import pe.idat.appinstituto.viewmodel.JustificacionViewModel
import pe.idat.appinstituto.viewmodel.ReservasViewModel

class JustificacionFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentJustificacionBinding? = null
    private val binding get() = _binding!!
    private lateinit var justificacionViewModel: JustificacionViewModel
    private lateinit var alumnoViewModel: AlumnoViewModel


    private var idAsistencia : String? = null
    private var fechaAsistencia: String? = null
    private var estadoAsistencia: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJustificacionBinding.inflate(
            inflater, container, false
        )
        justificacionViewModel = ViewModelProvider(requireActivity()).get(JustificacionViewModel::class.java)
        alumnoViewModel = ViewModelProvider(requireActivity()).get(AlumnoViewModel::class.java)


        idAsistencia = arguments?.getString("id_asistencia")
        fechaAsistencia = arguments?.getString("fecha")
        estadoAsistencia = arguments?.getString("estado")

        binding.codigoAsistencia.text = idAsistencia
        binding.fecha.text = fechaAsistencia
        binding.estado.text = estadoAsistencia

        binding.btnenviar.setOnClickListener(this)

        obtenerIDAlumno { idAlumno ->
            listarJustificacion(idAlumno)
        }

        justificacionViewModel.responseJustificacion.observe(viewLifecycleOwner,
            Observer {
                    response ->
                obtenerResultadoJustificacion(response)
                obtenerIDAlumno { idAlumno ->
                    listarJustificacion(idAlumno)
                }
            })

        binding.rvjustificaciones.layoutManager = LinearLayoutManager(
            requireActivity())

        if (binding.rvjustificaciones.adapter == null) {
            binding.rvjustificaciones.adapter = JustificacionAdapter(emptyList())
        }

        return binding.root
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnenviar -> registrarJustificacion()

        }
    }

    private fun obtenerResultadoJustificacion(response: ResponseJustificacion) {
        if(response.rpta){
            setearControles()
        }
        AppMensaje.mensaje(binding.root,
            response.mensaje, TipoMensaje.INFO)
    }

    fun listarJustificacion(idAlumno: String) {
        justificacionViewModel.listarJustificacion(idAlumno).observe(viewLifecycleOwner, Observer { response ->
            if (response.isNotEmpty()) {
                binding.rvjustificaciones.adapter = JustificacionAdapter(response)
            }
        })
    }

    private fun setearControles(){
        binding.codigoAsistencia.setText("")
        binding.fecha.setText("")
        binding.estado.setText("")
        binding.mtjustificar.setText("")

    }

    private fun registrarJustificacion() {
            justificacionViewModel.registroJustificacion(
                binding.codigoAsistencia.text.toString(),
                binding.mtjustificar.text.toString()
            )
    }

    private fun obtenerIDAlumno(callback: (String) -> Unit) {
        alumnoViewModel.obtener().observe(viewLifecycleOwner, Observer { obj ->
            obj?.let {
                callback(it.id_alumno)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}