package pe.idat.appinstituto.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.appinstituto.R
import pe.idat.appinstituto.databinding.FragmentReservaBinding
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import pe.idat.appinstituto.utilitarios.AppMensaje
import pe.idat.appinstituto.utilitarios.TipoMensaje

import pe.idat.appinstituto.view.adapter.ReservasAdapter
import pe.idat.appinstituto.viewmodel.AlumnoViewModel
import pe.idat.appinstituto.viewmodel.ReservasViewModel

class ReservaFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentReservaBinding? = null
    private val binding get() = _binding!!
    private lateinit var reservasViewModel: ReservasViewModel
    private lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservaBinding.inflate(
            inflater, container, false

        )
        reservasViewModel = ViewModelProvider(requireActivity()).get(ReservasViewModel::class.java)
        alumnoViewModel = ViewModelProvider(requireActivity()).get(AlumnoViewModel::class.java)

        binding.btnReservarAula.setOnClickListener(this)

        obtenerIDAlumno { idAlumno ->
            registrarReserva()
            listarReservas(idAlumno)
        }

        reservasViewModel.responseReservas.observe(viewLifecycleOwner, Observer { response ->
            obtenerResultadoRegistro(response)
            obtenerIDAlumno { idAlumno ->
                listarReservas(idAlumno)
            }})

        binding.rvreservas.layoutManager = LinearLayoutManager(
            requireActivity())

        return binding.root

    }

    fun listarReservas(idAlumno: String) {
        reservasViewModel.listarReservas(idAlumno).observe(viewLifecycleOwner, Observer { response ->
            binding.rvreservas.adapter = ReservasAdapter(response)
        })
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnReservarAula -> registrarReserva()
        }
    }

    private fun obtenerResultadoRegistro(response: ResponseReservas) {
        if(response.rpta){
            setearControles()
        }
        AppMensaje.mensaje(binding.root,
            response.mensaje, TipoMensaje.INFO)
    }

    private fun setearControles(){
        binding.etAula.setText("")
        binding.etFecha.setText("")
        binding.etHoraInicio.setText("")
        binding.etHoraFin.setText("")

    }
    private fun obtenerIDAlumno(callback: (String) -> Unit) {
        alumnoViewModel.obtener().observe(viewLifecycleOwner, Observer { obj ->
            obj?.let {
                callback(it.id_alumno)
            }
        })
    }
    private fun registrarReserva() {
        obtenerIDAlumno { idAlumno ->
            reservasViewModel.registroUsuario(
                idAlumno,
                binding.etAula.text.toString(),
                binding.etFecha.text.toString(),
                binding.etHoraInicio.text.toString(),
                binding.etHoraFin.text.toString(),
            )
        }
    }


}