package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnexionActivity extends AppCompatActivity {

    @BindView(R.id.activity_connexion_email_edt)
    EditText emailEditText;
    @BindView(R.id.activity_connexion_mdp_edt)
    EditText mdpEditText;
    @BindView(R.id.activity_connexion_subtitle)
    TextView subtitleTextView;

    ImageView connexionView_image;
    Button connexionView_connexion_btn;
    Button connexionView_inscription_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);

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
                NetworkProvider.getInstance().connexionPrestataire(emailEditText.getText().toString(), mdpEditText.getText().toString(), new NetworkProvider.Listener<List<Prestataire>>() {
                    @Override
                    public void onSuccess(List<Prestataire> data) {
                        if(data.isEmpty()){
                            subtitleTextView.setText("Email et/ou Mot de passe incorrect");
                            subtitleTextView.setTextColor(getResources().getColor(R.color.colorError));
                        } else {
                            Intent intent = new Intent(ConnexionActivity.this, AccueilActivity.class);

                            intent.putExtra("idPrestataire", data.get(0).getIdPrestataire());
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("errorConnexion", t.getMessage());
                    }
                });
            }
        });







    }



}
