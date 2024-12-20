package pe.idat.appinstituto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.databinding.ItemNotaCursoBinding
import pe.idat.appinstituto.retrofit.response.ResponseNotas

class NotasAdapter(
    private var listaNotas: List<ResponseNotas>)
    : RecyclerView.Adapter<NotasAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:
                           ItemNotaCursoBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int):
            NotasAdapter.ViewHolder {
        val binding = ItemNotaCursoBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:
                                  NotasAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listaNotas[position]){
                binding.txtNombreCurso.text = nombre_curso
                binding.txtNotaEval1.text = nota1
                binding.txtNotaEval2.text = nota2
                binding.txtNotaEval3.text = nota3
                binding.txtNotaExamenFinal.text = nota4
                binding.txtNotaPromedioFinal.text = promedio

            }
        }
    }

    override fun getItemCount() = listaNotas.size
}