package pe.idat.appinstituto.retrofit.response

data class ResponseLogin(
    var rpta: Boolean,
    var mensaje: String,
    var id_alumno: String,
    var nombre: String,
    var apellido: String,
    var password: String,
)
