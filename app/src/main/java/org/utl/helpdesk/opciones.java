package org.utl.helpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class opciones extends AppCompatActivity {

    ImageButton btnListar;
    ImageButton btnTickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        btnTickets = findViewById(R.id.btnTickets);
        btnListar = findViewById(R.id.btnListar);

        btnTickets.setOnClickListener(view ->{
            Intent abrirVista = new Intent(this,RegistroTicket.class);
            startActivity(abrirVista);
        });
        btnListar.setOnClickListener(view ->{
            Intent abrirVista = new Intent(this,ListadoTicket.class);
            startActivity(abrirVista);
        });
    }
}