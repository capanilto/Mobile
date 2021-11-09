package com.example.teste01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;
import static com.example.teste01.R.id.grav;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nome = (EditText) findViewById(R.id.pName);
        final EditText email = (EditText) findViewById(R.id.pEmail);

        Button gravar = (Button) findViewById(grav);
        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("preferences", Content.MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("nome", nome.getText().toString());
                ed.putString("email", email.getText()toString());
                ed.apply();
                Toast.makeText(getBaseContext(), "Gravado com sucesso", LENGTH_SHORT).show();
            }
        });



    }


}