package org.utl.helpdesk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewExitoso extends AppCompatActivity {

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exitoso);
        btnRegresar =  findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent abrirVista = new Intent(this,MainActivity.class);
            startActivity(abrirVista);
        });
    }
}