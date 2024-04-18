package org.utl.helpdesk;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.utl.helpdesk.api.Config;
import org.utl.helpdesk.api.LoginService;
import org.utl.helpdesk.model.Respuesta;
import org.utl.helpdesk.model.Saludar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtPassword;
    EditText txtUsuario;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsuario = findViewById(R.id.txtUsuario);
        /*
        btnLogin.setOnClickListener(view ->{
            iniciarSesion();
        });
        */

        btnLogin.setOnClickListener(view ->{
            Intent abrirVista = new Intent(MainActivity.this,opciones.class);
            startActivity(abrirVista);
        });


    }

    private void probarServicio() {
        String URL_BASE="http://10.0.2.2:8080/HelpDesk_Back/api/";
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<Respuesta> call = loginService.probarService();
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.code()==200){
                    Respuesta mensaje= response.body();
                    assert mensaje != null;
                    Toast.makeText(MainActivity.this,"EL mensaje es: "+ mensaje.getResponse(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fallo XD",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validar(){
        String user, password;
        user = String.valueOf(txtUsuario.getText());
        password = String.valueOf(txtPassword.getText());
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService api = retrofit.create(LoginService.class);
        Call<JsonObject> validar = api.validar(user,password);
        validar.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Toast.makeText(MainActivity.this,"realizado",Toast.LENGTH_SHORT).show();
                System.out.println(response.code());
                System.out.println(response.body());

                // validar si fue correcto
                JsonObject respuesta = response.body();
                if (respuesta != null){
                    if (respuesta.has("idUsuario")){
                        int id=respuesta.get("idUsuario").getAsInt();
                        if(id>0){
                            Toast.makeText(MainActivity.this,"respuesta Correcta "+id,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"respuesta incorrecta "+id,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }

    public void iniciarSesion(){
        String user, password;
        user = String.valueOf(txtUsuario.getText());
        password = String.valueOf(txtPassword.getText());
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService api = retrofit.create(LoginService.class);
        Call<JsonObject> validar = api.iniciarSesionService(user,password);
        validar.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Toast.makeText(MainActivity.this,"realizado",Toast.LENGTH_SHORT).show();
                System.out.println(response.code());
                System.out.println(response.body());

                // valdiar si fue correcto
                JsonObject respuesta = response.body();
                if (respuesta != null){
                    if (respuesta.has("idUsuario")){
                        int id=respuesta.get("idUsuario").getAsInt();
                        if(id>0){
                            Toast.makeText(MainActivity.this,"respuesta Correcta "+id,Toast.LENGTH_SHORT).show();
                            Intent abrirVista = new Intent(MainActivity.this,opciones.class);
                            startActivity(abrirVista);
                        }else{
                            Toast.makeText(MainActivity.this,"respuesta incorrecta "+id,Toast.LENGTH_SHORT).show();
                            Intent abrirVista2 = new Intent(MainActivity.this,ViewError.class);
                            startActivity(abrirVista2);
                        }}}
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                System.out.println("antes de");
                System.err.println(t.getMessage());
                System.out.println("despues de");
            }
        });
    }
}