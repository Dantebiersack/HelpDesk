package org.utl.helpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.utl.helpdesk.api.ApiTicket;
import org.utl.helpdesk.api.Config;
import org.utl.helpdesk.model.Categoria;
import org.utl.helpdesk.model.Empleado;
import org.utl.helpdesk.model.Ticket;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroTicket extends AppCompatActivity {

    Spinner spCategoria;
    ArrayList<String> listaCategorias = new ArrayList<>();
    ArrayList<Categoria> listaCategorias2 = new ArrayList<Categoria>();

    EditText txtDescripcion;
    EditText txtDispositivo;
    Button btnRegistrar;
    ArrayAdapter<String> adapterCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ticket);

        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtDispositivo = findViewById(R.id.txtDispositivo);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        spCategoria = findViewById(R.id.spCategoria);
        cargarCategorias();
        /*
        listaCategorias.add("Laptop");
        listaCategorias.add("Desktop");
        listaCategorias.add("Tablet");
        listaCategorias.add("Scanner");
        listaCategorias.add("Copiadora");
        listaCategorias.add("Smart phone");
        listaCategorias.add("Monitor");

        adapterCategoria = new ArrayAdapter<>(
          RegistroTicket.this, android.R.layout.simple_spinner_dropdown_item);
        adapterCategoria.addAll(listaCategorias);
        spCategoria.setAdapter(adapterCategoria);
        */
        btnRegistrar.setOnClickListener(v->{
            save();
        });


        Ticket t = new Ticket();

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void cargarCategorias(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiTicket api = retrofit.create(ApiTicket.class);
        Call<ArrayList<Categoria>> cat = api.getAllCategoria();
        cat.enqueue(new Callback<ArrayList<Categoria>>() {
            @Override
            public void onResponse(Call<ArrayList<Categoria>> call, Response<ArrayList<Categoria>> response) {
                listaCategorias2.addAll(response.body());
                ArrayList<String> categorias = new ArrayList<>();
                for (Categoria c:listaCategorias2) {
                    categorias.add(c.getCategoria());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistroTicket.this, android.R.layout.simple_spinner_item, categorias);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCategoria.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {

            }
        });
    }

    public void save(){
        String dispositivo = txtDispositivo.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        int idEmpleado = 1;
        Gson gson = new Gson();
        Ticket t = new Ticket();
        Categoria c = new Categoria();
        Empleado e = new Empleado();
        e.setIdEmpleado(idEmpleado);

        String selectedCategoria = spCategoria.getSelectedItem().toString();
        int id=0;
        for (Categoria ca:listaCategorias2) {
            if(ca.getCategoria().equals(selectedCategoria)){
                id = ca.getIdCategoria();
                break;
            }
        }
        c.setIdCategoria(id);
        t.setIdTicket(0);
        t.setDispositivo(dispositivo);
        t.setDescripcion(descripcion);
        t.setCategoria(c);
        t.setEmpleado(e);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiTicket api = retrofit.create(ApiTicket.class);
        Call<JsonObject> guardar = api.saveTicket(gson.toJson(t));
        guardar.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                System.out.println(response.code());
                System.out.println(response.body());
                JsonObject respuesta = response.body();
                if (response.code() == 200){
                    Toast.makeText(RegistroTicket.this,"Registro realizado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistroTicket.this,"Registro no realizado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}