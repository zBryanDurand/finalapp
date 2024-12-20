package pe.idat.appinstituto.retrofit.response

data class ResponseAsistencia(
    var id_asistencia:String,
    var id_alumno:String,
    var nombre_curso: String,
    var fecha: String,
    var estado: String,
)
