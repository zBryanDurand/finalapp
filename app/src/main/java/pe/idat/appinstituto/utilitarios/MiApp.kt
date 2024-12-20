package pe.idat.appinstituto.utilitarios

import android.app.Application

class MiApp : Application() {
    companion object {
        lateinit var instancia: MiApp
    }

    override fun onCreate() {
        super.onCreate()
        instancia = this
    }

}