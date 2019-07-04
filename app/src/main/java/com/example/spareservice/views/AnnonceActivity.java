package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnonceActivity extends AppCompatActivity {

    @BindView(R.id.activity_annonce_logo_image)
    ImageView imageLogo;
    @BindView(R.id.activity_annonce_title)
    TextView title;
    @BindView(R.id.activity_annonce_subtitle)
    TextView subtitle;
    @BindView(R.id.activity_annonce_description)
    TextView description;
    @BindView(R.id.activity_annonce_detail)
    TextView detail;
    @BindView(R.id.activity_annonce_postuler_btn)
    Button postulerBtn;

    String serviceNom;
    String serviceType;
    String nomClient;
    String idPrestataire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);
        ButterKnife.bind(this);

        Glide.with(AnnonceActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        Intent intent = getIntent();
        serviceNom = intent.getStringExtra("serviceNom");
        serviceType = intent.getStringExtra("serviceType");
        idPrestataire = intent.getStringExtra("idPrestataire");
        Log.d("presta", idPrestataire);

        Gson g = new Gson();
        String str = g.toJson(intent.getSerializableExtra("annonce"));
        Annonce annonce = g.fromJson(str, Annonce.class);


        Gson gg = new Gson();
        String str2 = gg.toJson(intent.getSerializableExtra("client"));
        Client client = gg.fromJson(str2, Client.class);

        nomClient = client.getNom();

        title.setText(serviceType);
        subtitle.setText(serviceNom);
        description.setText(annonce.getDescriptionAnnonce());
        detail.setText(client.getNom() + "\n" + client.getPrenom() + "\n" + client.getEmail() + "\n\n" + client.getTel() + "\n\n" + annonce.getDetailAnnonce() + "\n" );

        postulerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AnnonceActivity.this, AnnonceDetailActivity.class);
                intent1.putExtra("annonce", annonce);
                intent1.putExtra("client", client);
                intent1.putExtra("serviceNom", serviceNom);
                intent1.putExtra("serviceType", serviceType);
                intent1.putExtra("idPrestataire", idPrestataire);
                startActivity(intent1);
                onPause();
            }
        });
    }
}
