package pe.idat.appinstituto.retrofit.response

data class ResponseReservas(
    var nombre_aula: String,
    var fecha_reserva: String,
    var hora_inicio: String,
    var hora_fin: String,

    var rpta: Boolean,
    var mensaje: String
)