package com.example.spareservice.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;

public class InscriptionActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        logo = findViewById(R.id.activity_inscription_logo_image);
        Glide.with(InscriptionActivity.this).load(R.drawable.spareservicelogomini).into(logo);
    }
}
