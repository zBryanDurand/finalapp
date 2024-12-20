package pe.idat.appinstituto.retrofit.request

data class RequestReservas(
    var id_alumno:String,
    var id_aula: String,
    var fecha_reserva: String,
    var hora_inicio: String,
    var hora_fin: String,
)