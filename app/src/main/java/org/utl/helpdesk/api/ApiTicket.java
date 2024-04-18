package org.utl.helpdesk.api;

import com.google.gson.JsonObject;

import org.utl.helpdesk.model.Categoria;
import org.utl.helpdesk.model.Ticket;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiTicket {

    @FormUrlEncoded
    @POST("ticket/save")
    Call<JsonObject> saveTicket(@Field("ticket") String ticket);

    @GET("categoria/getAll")
    Call<ArrayList<Categoria>> getAllCategoria();

    @GET("ticket/getAll")
    Call<ArrayList<Ticket>> getAllTicket();
}
