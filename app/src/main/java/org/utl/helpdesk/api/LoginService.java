package org.utl.helpdesk.api;

import com.google.gson.JsonObject;

import org.utl.helpdesk.model.Respuesta;
import org.utl.helpdesk.model.Saludar;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @GET("saludo/saludar")
    Call<Saludar>pedirSaludo();

    @FormUrlEncoded
    @POST("saludo/validar")
    Call<JsonObject> validar(@Field("usuario") String usuario,
                             @Field("contrasenia") String contrasenia);

    @GET("login/saludar")
    Call<Respuesta> probarService();

    @FormUrlEncoded
    @POST("login/generarToken2")
    Call<JsonObject> iniciarSesionService(@Field("user") String user,
                             @Field("password") String password);
}
