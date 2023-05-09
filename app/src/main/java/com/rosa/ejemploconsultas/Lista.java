package com.rosa.ejemploconsultas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView list;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btn_listar;

   // String[] preguntass={"Selecciona tu pregunta","Pregunta 1","Pregunta 2","Pregunta 3",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list=findViewById(R.id.lista_respuestas);
        btn_listar=findViewById(R.id.btn_listar);
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });




    }

    public void listar() {

        //CREO CONEXION DE BD
        database = FirebaseDatabase.getInstance(); //conexion
        myRef = database.getReference(); // referencia

        // CREO MI ARRAYLIST PARA ALMACENAR LOS DATOS DE LA CONSULTA

        ArrayList<Preguntas> listapreguntas = new ArrayList<>();


        myRef.child("Preguntas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
        // creo un ciclo para recorrer los dato almacenados
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Preguntas pregunta = dataSnapshot.getValue(Preguntas.class);
                    listapreguntas.add(pregunta);
                    ArrayAdapter adapter =new ArrayAdapter(Lista.this, android.R.layout.simple_list_item_1,listapreguntas);
                    list.setAdapter(adapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}