package pe.idat.appinstituto.retrofit.response

data class ResponseJustificacion(

    var fecha: String,
    var estado: String,
    var nombre_curso: String,
    var motivo: String,


    var rpta: Boolean,
    var mensaje: String
)