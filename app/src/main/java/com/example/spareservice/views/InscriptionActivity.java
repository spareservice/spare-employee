package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscriptionActivity extends AppCompatActivity {

    @BindView(R.id.activity_inscription_logo_image)
    ImageView logo;
    @BindView(R.id.activity_inscription_subTitle)
    TextView subtitleTextView;
    @BindView(R.id.activity_inscription_nom_edt)
    EditText nomEditText;
    @BindView(R.id.activity_inscription_prenom_edt)
    EditText prenomEditText;
    @BindView(R.id.activity_inscription_email_edt)
    EditText emailEditText;
    @BindView(R.id.activity_inscription_mdp_edt)
    EditText mdpEditText;
    @BindView(R.id.activity_inscription_confMdp_edt)
    EditText confMdpEditText;
    @BindView(R.id.activity_inscription_tel_edt)
    EditText telEditText;
    @BindView(R.id.activity_inscription_adresse_edt)
    EditText adresseTextView;
    @BindView(R.id.activity_inscription_cp_edt)
    EditText cpTextView;
    @BindView(R.id.activity_inscription_ville_edt)
    EditText villeTextView;
    @BindView(R.id.activity_connexion_inscrire_btn)
    Button inscrireBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ButterKnife.bind(this);

        Glide.with(InscriptionActivity.this).load(R.drawable.spareservicelogomini).into(logo);

        inscrireBtn.setOnClickListener(v -> {
            Intent intent = new Intent(InscriptionActivity.this, ContratActivity.class);

            if ((mdpEditText.getText().toString().equals(confMdpEditText.getText().toString()) && !mdpEditText.getText().toString().isEmpty())){
                if(mdpEditText.getText().length() <= 5) {
                    if (!validEmail(emailEditText.getText().toString())) {
                        Toast.makeText(InscriptionActivity.this,"Entrer un email valide",Toast.LENGTH_LONG).show();
                    } else {
                        NetworkProvider.getInstance().addPrestataire(nomEditText.getText().toString(), prenomEditText.getText().toString(),
                                emailEditText.getText().toString(), mdpEditText.getText().toString(),
                                telEditText.getText().toString(), adresseTextView.getText().toString(),
                                cpTextView.getText().toString(), villeTextView.getText().toString(), new NetworkProvider.Listener<List<Prestataire>>() {
                                    @Override
                                    public void onSuccess(List<Prestataire> data) {
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        Log.d("errorAddingPrestataire", t.getMessage());
                                    }
                                });
                    }
                } else {
                    Toast.makeText(InscriptionActivity.this,"Ajouter un mot de passe avec plus de 5 caractères",Toast.LENGTH_LONG).show();
                }
            } else {
                subtitleTextView.setText("Le mot de passe n'est pas identique");
                Toast.makeText(InscriptionActivity.this,"Le mot de passe n'est pas identique",Toast.LENGTH_LONG).show();
                subtitleTextView.setTextColor(getResources().getColor(R.color.colorError));
            }

        });
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
