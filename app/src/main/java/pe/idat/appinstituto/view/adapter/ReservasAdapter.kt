package pe.idat.appinstituto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.databinding.ItemReservaBinding

import pe.idat.appinstituto.retrofit.response.ResponseReservas

class ReservasAdapter(
    private var listaReservas: List<ResponseReservas>)
    : RecyclerView.Adapter<ReservasAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding:
                           ItemReservaBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int):
            ReservasAdapter.ViewHolder {
        val binding = ItemReservaBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:
                                  ReservasAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listaReservas[position]){
                binding.txtFechaReserva.text = fecha_reserva
                binding.txtAula.text = nombre_aula
                binding.txtHoraInicio.text = hora_inicio
                binding.txtHoraFin.text = hora_fin
            }
        }
    }

    override fun getItemCount() = listaReservas.size
}