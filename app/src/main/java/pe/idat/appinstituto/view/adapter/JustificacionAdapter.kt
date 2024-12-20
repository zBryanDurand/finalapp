package pe.idat.appinstituto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.databinding.ItemJustificacionBinding
import pe.idat.appinstituto.databinding.ItemReservaBinding
import pe.idat.appinstituto.retrofit.response.ResponseJustificacion

import pe.idat.appinstituto.retrofit.response.ResponseReservas

class JustificacionAdapter(
    private var listaJustificacion: List<ResponseJustificacion>)
    : RecyclerView.Adapter<JustificacionAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding:
                           ItemJustificacionBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int):
            JustificacionAdapter.ViewHolder {
        val binding = ItemJustificacionBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:
                                  JustificacionAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listaJustificacion[position]){
                binding.txtFecha.text = fecha
                binding.txtEstado.text = estado
                binding.txtCurso.text = nombre_curso
                binding.txtMotivo.text = motivo
            }
        }
    }

    override fun getItemCount() = listaJustificacion.size


}