package pe.idat.appinstituto.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.idat.appinstituto.R

class EvaluacionAdapter(private val evaluaciones: List<Evaluacion>) :
    RecyclerView.Adapter<EvaluacionAdapter.EvaluacionViewHolder>() {

    class EvaluacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreEvaluacion: TextView = view.findViewById(R.id.tv_nombre_evaluacion)
        val nota: TextView = view.findViewById(R.id.tv_nota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evaluacion, parent, false)
        return EvaluacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvaluacionViewHolder, position: Int) {
        val evaluacion = evaluaciones[position]
        holder.nombreEvaluacion.text = evaluacion.nombre
        holder.nota.text = evaluacion.nota.toString()
    }

    override fun getItemCount(): Int = evaluaciones.size

}