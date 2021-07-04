package com.example.permis_de_conduire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        EditText edt_message = findViewById(R.id.edt_message);

        Log.i("DIM","Insertion du message de la première activité dans la seconde");
        edt_message.setText(intent.getStringExtra("message"));
    }

    public void envoyer_reponse(View view) {

        Log.i("DIM","Envoi du second message à la première activité");
        EditText edit = findViewById(R.id.edt_message);
        Intent intent = new Intent();

        intent.putExtra("resultat",edit.getText().toString());
        setResult(SecondActivity.RESULT_OK,intent);
        finish();

    }

}
