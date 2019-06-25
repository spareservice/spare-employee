package com.example.spareservice.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    @BindView(R.id.activity_annonce_detail_date)
    TextView dateTextView;
    @BindView(R.id.activity_annonce_detail_time)
    TextView timeTextView;
    @BindView(R.id.activity_annonce_detail_date_btn)
    Button dateBtn;
    @BindView(R.id.activity_annonce_detail_time_btn)
    Button timeBtn;
    @BindView(R.id.activity_annonce_detail_confirmer_btn)
    Button confirmerBtn;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_detail);
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


        Glide.with(AnnonceDetailActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AnnonceDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateTextView.setText(String.format("%02d", dayOfMonth) + "/" + String.format("%02d", (month + 1)) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(AnnonceDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeTextView.setText(String.format("%02d", hourOfDay) + "h" + String.format("%02d", minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        confirmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateTextView.getText().toString().isEmpty() || dateTextView.getText().toString().equals("Veuillez choisir une date")) {
                    dateTextView.setText("Veuillez choisir une date");
                    dateTextView.setTextColor(getResources().getColor(R.color.colorError));
                } else if (timeTextView.getText().toString().isEmpty() || timeTextView.getText().toString().equals("Veuillez choisir une heure")) {
                    timeTextView.setText("Veuillez choisir une heure");
                    timeTextView.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    Intent intent1 = new Intent(AnnonceDetailActivity.this, AnnonceResumeActivity.class);
                    intent1.putExtra("annonce", annonce);
                    intent1.putExtra("client", client);
                    intent1.putExtra("idPrestataire", idPrestataire);
                    intent1.putExtra("serviceNom", serviceNom);
                    intent1.putExtra("serviceType", serviceType);
                    intent1.putExtra("date", dateTextView.getText().toString());
                    intent1.putExtra("time", timeTextView.getText().toString());
                    startActivity(intent1);
                    onPause();
                }

            }
        });


    }
}
