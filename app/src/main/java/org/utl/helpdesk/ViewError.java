package org.utl.helpdesk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewError extends AppCompatActivity {
    Button btnVolver;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_error);

        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(view ->{
            Intent abrirVista = new Intent(this,MainActivity.class);
            startActivity(abrirVista);
        });
    }
}