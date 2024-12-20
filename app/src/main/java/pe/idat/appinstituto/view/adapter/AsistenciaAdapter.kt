package pe.idat.appinstituto.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.R
import pe.idat.appinstituto.databinding.ItemAsistenciaBinding
import pe.idat.appinstituto.retrofit.response.ResponseAsistencia

class AsistenciaAdapter(
    private var listaAsistencia: List<ResponseAsistencia>,
    private val onJustificarClick: (ResponseAsistencia) -> Unit
)

    : RecyclerView.Adapter<AsistenciaAdapter.ViewHolder>() {
        inner class ViewHolder(val binding:
                                    ItemAsistenciaBinding)
            :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsistenciaAdapter.ViewHolder {
        val binding = ItemAsistenciaBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsistenciaAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(listaAsistencia[position]) {
                binding.txtIdAsistencia.text = id_asistencia
                binding.tvasisfecha.text = fecha
                binding.tvasisestado.text = estado
                binding.tvcurso.text = nombre_curso

                if (estado == "presente") {
                    binding.btnasisjusti.isEnabled = false
                } else {
                    binding.btnasisjusti.isEnabled = true
                }
            }
            binding.btnasisjusti.setOnClickListener {
                onJustificarClick(listaAsistencia[position])
            }
        }
    }

    override fun getItemCount() = listaAsistencia.size

    }


