package pe.idat.appinstituto.view.adapter

import android.widget.ArrayAdapter
import android.widget.Spinner
import pe.idat.appinstituto.R
import androidx.databinding.BindingAdapter

object CursosAdapter {

    @JvmStatic
    @BindingAdapter("cursos")
    fun setSpinnerEntries(spinner: Spinner, items: List<String>?) {
        items?.let {
            val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}