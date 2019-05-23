package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscriptionActivity extends AppCompatActivity {

    @BindView(R.id.activity_inscription_logo_image)
    ImageView logo;
    @BindView(R.id.activity_connexion_inscrire_btn)
    Button inscrireBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ButterKnife.bind(this);

        Glide.with(InscriptionActivity.this).load(R.drawable.spareservicelogomini).into(logo);

    }
}
