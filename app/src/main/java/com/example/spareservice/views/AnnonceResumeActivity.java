package com.example.spareservice.views;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Mission;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.service.NetworkProvider;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnonceResumeActivity extends AppCompatActivity {

    @BindView(R.id.activity_annonce_resume_logo_image)
    ImageView imageLogo;
    @BindView(R.id.activity_annonce_resume_title)
    TextView titleTextView;
    @BindView(R.id.activity_annonce_resume_description)
    TextView descriptionTextView;
    @BindView(R.id.activity_annonce_resume_detail)
    TextView detailTextView;
    @BindView(R.id.activity_annonce_resume_horaire)
    TextView horaireTextView;
    @BindView(R.id.activity_annonce_resumer_finaliser_btn)
    Button finaliserBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_resume);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        Gson g = new Gson();
        String str = g.toJson(intent.getSerializableExtra("annonce"));
        Annonce annonce = g.fromJson(str, Annonce.class);

        Gson gg = new Gson();
        String str2 = gg.toJson(intent.getSerializableExtra("client"));
        Client client = gg.fromJson(str2, Client.class);

        String serviceNom = intent.getStringExtra("serviceNom");
        String serviceType = intent.getStringExtra("serviceType");
        String idPrestataire = intent.getStringExtra("idPrestataire");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        Glide.with(AnnonceResumeActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        titleTextView.setText("Résumé");
        descriptionTextView.setText(serviceNom + "\n" + serviceType + "\n" + annonce.getDescriptionAnnonce());
        detailTextView.setText(client.getNom() + "\n" + client.getPrenom() + "\n" + client.getEmail() + "\n" + annonce.getDetailAnnonce() + "\n" + client.getTel());
        horaireTextView.setText(date + "\n" + time);

        finaliserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkProvider.getInstance().addMission(annonce.getIdAnnonce(), idPrestataire, client.getIdClient(), date, time, false, false, new NetworkProvider.Listener<List<Mission>>() {
                    @Override
                    public void onSuccess(List<Mission> data) {
                        new AlertDialog.Builder(AnnonceResumeActivity.this)
                                .setTitle("Posté")
                                .setMessage("Vous aurez une réponse d'ici sous peu")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent1 = new Intent(AnnonceResumeActivity.this, AccueilActivity.class);
                                        startActivity(intent1);
                                        finish();
                                    }
                                })
                                .show();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
            }
        });


    }
}
