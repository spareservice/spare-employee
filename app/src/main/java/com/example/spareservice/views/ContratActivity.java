package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spareservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContratActivity extends AppCompatActivity {

    @BindView(R.id.activity_contrat_title)
    TextView title;
    @BindView(R.id.activity_contrat_contenu)
    TextView contenu;
    @BindView(R.id.activity_contrat_btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrat);
        ButterKnife.bind(this);

        title.setText("Contrat");
        contenu.setText("Bienvenue dans SpareService, le domaine de l'expertise pour tous vos besoins.\n" +
                "Cette application est un passe-temps vous permettant de gagner un peu de sous dans votre vie quotienne, en aidant les gens à satisfaire leur besoin.\n" +
                "Sur cette application, vous pouvez postuler à des annonces et vous pouvez ajouter vos critères nécessaire pour répondre aux besoins du client. " +
                "Si le client est d'accord, alors votre mission sera accepté, et vous devrez allez dans la mission, qui se situe dans la rubrique \"Mission\"," +
                " puis cliquer sur \"Commencer\" pour démarrer la mission, un timer se mettera en route. Et lorsque vous aurez terminé la mission, vous devrez" +
                " cliquer sur \"Terminer\" pour finaliser la mission, vous serez donc payer en fonction des heures effectuées et du prix fixé, qui est de 0,20$/min." +
                "Attention, vous devez obligatoirement avoir un compte PayPal pour pouvoir communiquer vos coordonnées lors de la transaction." +
                " Voila tout.");
        Intent intent = new Intent(ContratActivity.this, ConnexionActivity.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

    }
}
