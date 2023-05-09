package com.rosa.ejemploconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

        List<Preguntas> lista = new ArrayList<>();
        EditText preguntas, r1,r2,r3,r4;
        Button btninsertar, btnlistar;
        Spinner spn1, spnNivel;
        ListView listas;
        FirebaseDatabase database; // variable para  la conexion de fire base//
        DatabaseReference myRef; // variable para obtener la referencia//


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            referenciar();
    }

    private void referenciar() {
    }
}