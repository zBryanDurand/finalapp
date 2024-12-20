package pe.idat.appinstituto.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.view.adapter.Evaluacion
import pe.idat.appinstituto.view.adapter.EvaluacionAdapter
import pe.idat.appinstituto.R
import pe.idat.appinstituto.databinding.FragmentNotasBinding
import pe.idat.appinstituto.view.adapter.NotasAdapter
import pe.idat.appinstituto.viewmodel.AlumnoViewModel
import pe.idat.appinstituto.viewmodel.NotasViewModel

class NotasFragment : Fragment() {

    private var _binding: FragmentNotasBinding? = null
    private val binding get() = _binding!!
    private lateinit var notasViewModel: NotasViewModel
    private lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotasBinding.inflate(inflater, container, false)

        binding.rvnotas.layoutManager = LinearLayoutManager(requireActivity())

        notasViewModel = ViewModelProvider(requireActivity()).get(NotasViewModel::class.java)
        alumnoViewModel = ViewModelProvider(requireActivity()).get(AlumnoViewModel::class.java)

        obtenerIDAlumno { idAlumno ->
            listarNotas(idAlumno)
        }

        return binding.root
    }

    private fun obtenerIDAlumno(callback: (String) -> Unit) {
        alumnoViewModel.obtener().observe(viewLifecycleOwner, Observer { obj ->
            obj?.let {
                callback(it.id_alumno)  // Llamar al callback con el id_alumno
            }
        })
    }

    fun listarNotas(idAlumno: String) {
        notasViewModel.listarNotas(idAlumno).observe(viewLifecycleOwner, Observer { response ->
            binding.rvnotas.adapter = NotasAdapter(response)
        })
    }
}