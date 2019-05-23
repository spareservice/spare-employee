package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
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

    String serviceNom;
    String serviceType;
    String nomClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);
        ButterKnife.bind(this);

        Glide.with(AnnonceActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        Intent intent = getIntent();
        serviceNom = intent.getStringExtra("serviceNom");
        serviceType = intent.getStringExtra("serviceType");

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
        detail.setText(client.getNom() + "\n" + client.getPrenom() + "\n" + client.getEmail() + "\n" + annonce.getDetailAnnonce() + "\n" + client.getTel());


    }
}
