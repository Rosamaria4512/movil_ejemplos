package com.rosa.ejemploconsultas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ejemploconsulta-22a37-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usuario = findViewById(R.id.usuario);
        final EditText password = findViewById(R.id.password_login);
        final Button loginbtn = findViewById(R.id.login_btn);
        final TextView registrarseahora = findViewById(R.id.registrarse_ahora);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  String textTxt = usuario.getText().toString();
                final  String passwordTxt = password.getText().toString();

                if(textTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Login.this,"por favor ingrese con su usuario o contraseña", Toast.LENGTH_SHORT).show();

                }
                else{
                    database.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //comprobar si existe usuario en la bd
                            if(snapshot.hasChild(textTxt)) {
                                // usuario si existe en la bd
                                //ahora obtenga la contraseña del usuario de la bd y conéctela con la contraseña ingresada por el usuario
                                final String getPassword = snapshot.child(textTxt).child("contraseña").getValue(String.class);

                                if (getPassword.equals(passwordTxt)) {
                                    Toast.makeText(Login.this, "inicio sesión con éxito", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Login.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();



                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        registrarseahora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, RegisterTuSena.class));
            }
        });


    }
}