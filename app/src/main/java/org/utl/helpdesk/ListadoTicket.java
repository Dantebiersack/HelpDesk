package org.utl.helpdesk;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import org.utl.helpdesk.api.ApiTicket;
import org.utl.helpdesk.api.Config;
import org.utl.helpdesk.model.Ticket;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoTicket extends AppCompatActivity {
    ArrayList<Ticket> listTickets = new ArrayList<>();
    Context context = ListadoTicket.this;
    RecyclerView recyclerViewTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_ticket);
        recyclerViewTickets = findViewById(R.id.recyclerViewTickets);
        cargarTickets();
    }

    public void cargarTickets(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiTicket api = retrofit.create(ApiTicket.class);
        Call<ArrayList<Ticket>> tik = api.getAllTicket();
        tik.enqueue(new Callback<ArrayList<Ticket>>() {
            @Override
            public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                assert response.body() != null;
                System.out.println(response.body());
                listTickets.addAll(response.body());
                recyclerViewTickets.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewTickets.setAdapter(new AdapterTicket(context,listTickets));
            }
            @Override
            public void onFailure(@NonNull Call<ArrayList<Ticket>> call, Throwable t) {

            }
        });
    }
}