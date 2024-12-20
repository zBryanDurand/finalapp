package pe.idat.appinstituto.retrofit

import pe.idat.appinstituto.retrofit.request.RequestJustificacion
import pe.idat.appinstituto.retrofit.request.RequestLogin
import pe.idat.appinstituto.retrofit.request.RequestReservas
import pe.idat.appinstituto.retrofit.response.ResponseAsistencia
import pe.idat.appinstituto.retrofit.response.ResponseCurso
import pe.idat.appinstituto.retrofit.response.ResponseJustificacion
import pe.idat.appinstituto.retrofit.response.ResponseLogin
import pe.idat.appinstituto.retrofit.response.ResponseNotas
import pe.idat.appinstituto.retrofit.response.ResponseReservas
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface InstitutoServicio {

    @POST("login")
    fun login(@Body loginRequest: RequestLogin):
            Call<ResponseLogin>

    @GET("notas")
    fun listarNotasPorCursoService(
        @Query("id_alumno") idAlumno: String,
    ):Call<List<ResponseNotas>>

    @GET("reservar")
    fun listarReservasService(
        @Query("id_alumno") idAlumno: String,
    ):Call<List<ResponseReservas>>

    @POST("reservar")
    fun registroReservar(@Body requestRegistro: RequestReservas):
            Call<ResponseReservas>

    @POST("justificacion")
    fun registroJustificacion(@Body requestJustificacion: RequestJustificacion):
            Call<ResponseJustificacion>

    @GET("asistencia")
    fun listarAsistencia(
        @Query("id_alumno") idAlumno: String,
    ): Call<List<ResponseAsistencia>>

    @GET("justificacion")
    fun listarJutificacion(
        @Query("id_alumno") idAlumno: String,
    ): Call<List<ResponseJustificacion>>

    @GET("notas")
    fun listarCurso(
        @Query("id_alumno") idAlumno: String,
        @Query("id_curso") idCurso: Int
    ): Call<List<ResponseCurso>>

    @GET("cursos")
    fun getItems(): Call<List<String>>





}