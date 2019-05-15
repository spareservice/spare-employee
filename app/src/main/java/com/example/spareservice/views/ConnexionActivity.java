package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;

public class ConnexionActivity extends AppCompatActivity {

    ImageView connexionView_image;
    Button connexionView_connexion_btn;
    Button connexionView_inscription_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        connexionView_image = findViewById(R.id.activity_connexion_logo_image);
        Glide.with(ConnexionActivity.this).load(R.drawable.spareservicelogomini).into(connexionView_image);

        connexionView_connexion_btn = findViewById(R.id.activity_connexion_connect_btn);
        connexionView_inscription_btn = findViewById(R.id.activity_connexion_inscrire_btn);

        connexionView_inscription_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                startActivity(intent);
                onPause();

            }
        });

        connexionView_connexion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionActivity.this, AccueilActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }



}
