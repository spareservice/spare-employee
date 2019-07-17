package com.example.spareservice.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnonceDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_annonce_detail_logo_image)
    ImageView imageLogo;
    @BindView(R.id.activity_annonce_detail_info_edt)
    EditText infoEdt;
    @BindView(R.id.activity_annonce_detail_confirmer_btn)
    Button confirmerBtn;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_detail);
        ButterKnife.bind(this);

        prefs = this.getSharedPreferences("preferences", MODE_PRIVATE);

        Intent intent = getIntent();

        Gson g = new Gson();
        String str = g.toJson(intent.getSerializableExtra("annonce"));
        Annonce annonce = g.fromJson(str, Annonce.class);

        Gson gg = new Gson();
        String str2 = gg.toJson(intent.getSerializableExtra("client"));
        Client client = gg.fromJson(str2, Client.class);

        String serviceNom = intent.getStringExtra("serviceNom");
        String serviceType = intent.getStringExtra("serviceType");
        //String idPrestataire = intent.getStringExtra("idPrestataire");
        String idPresta = prefs.getString("idPrestataire", "none");

        Glide.with(AnnonceDetailActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        confirmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(AnnonceDetailActivity.this, AnnonceResumeActivity.class);
                intent1.putExtra("annonce", annonce);
                intent1.putExtra("client", client);
                intent1.putExtra("idPrestataire", idPresta);
                intent1.putExtra("serviceNom", serviceNom);
                intent1.putExtra("serviceType", serviceType);
                intent1.putExtra("info", infoEdt.getText().toString());
                startActivity(intent1);
                onPause();


            }
        });


    }
}
