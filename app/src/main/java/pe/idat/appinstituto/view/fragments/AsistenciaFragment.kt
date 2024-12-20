package pe.idat.appinstituto.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.appinstituto.R
import pe.idat.appinstituto.databinding.FragmentAsistenciaBinding
import pe.idat.appinstituto.retrofit.response.ResponseAsistencia
import pe.idat.appinstituto.view.adapter.AsistenciaAdapter
import pe.idat.appinstituto.viewmodel.AlumnoViewModel
import pe.idat.appinstituto.viewmodel.AsistenciaViewModel
import androidx.navigation.fragment.findNavController


class AsistenciaFragment : Fragment() {

    private var _binding: FragmentAsistenciaBinding? = null
    private val binding get() = _binding!!
    private lateinit var asistenciaViewModel: AsistenciaViewModel
    private lateinit var alumnoViewModel: AlumnoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAsistenciaBinding.inflate(
            inflater, container, false
        )
        alumnoViewModel = ViewModelProvider(requireActivity()).get(AlumnoViewModel::class.java)

        binding.rvlistado.layoutManager = LinearLayoutManager(
            requireActivity())
        asistenciaViewModel = ViewModelProvider(requireActivity())
            .get(AsistenciaViewModel::class.java)

        obtenerIDAlumno { idAlumno ->
            listarAsistencia(idAlumno)
        }
        return binding.root
    }

    fun listarAsistencia(idAlumno: String) {
        asistenciaViewModel.listarAsistencia(idAlumno).observe(
            viewLifecycleOwner
        ) { listaAsistencia ->
            binding.rvlistado.adapter = AsistenciaAdapter(listaAsistencia) { asistencia ->
                irAJustificacion(asistencia.id_asistencia,asistencia.fecha,asistencia.estado)
            }
        }
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


    fun irAJustificacion(idAsistencia: String, fecha: String, estado: String) {
        val bundle = Bundle().apply {
            putString("id_asistencia", idAsistencia)  // Pasar el id_asistencia
            putString("fecha", fecha)                  // Pasar la fecha
            putString("estado", estado)                // Pasar el estado// Pasar el id_asistencia a través del bundle
        }
        findNavController().navigate(R.id.action_asistenciaFragment_to_justificacionFragment, bundle)
    }
}