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
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Mission;
import com.example.spareservice.data.service.MySpareService;
import com.example.spareservice.data.service.NetworkProvider;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MissionDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_mission_detail_logo_image)
    ImageView imageLogo;
    @BindView(R.id.activity_mission_detail_title)
    TextView titleTextView;
    @BindView(R.id.activity_mission_detail_subtitle)
    TextView subtitleTextView;
    @BindView(R.id.activity_mission_detail_description)
    TextView descriptionTextView;
    @BindView(R.id.activity_mission_detail_detail)
    TextView detailTextView;
    @BindView(R.id.activity_mission_detail_horaire)
    TextView horaireTextView;
    @BindView(R.id.activity_mission_detail_info)
    TextView infoTextView;
    @BindView(R.id.activity_mission_detail_finaliser_btn)
    Button finaliserButton;
    @BindView(R.id.activity_mission_detail_annuler_btn)
    Button annulerButton;
    @BindView(R.id.activity_mission_detail_timer)
    TextView timerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        ButterKnife.bind(this);
        //annulerButton.setVisibility(View.GONE);

        Glide.with(MissionDetailActivity.this).load(R.drawable.spareservicelogomini).into(imageLogo);

        Intent intent = getIntent();

        Gson g = new Gson();
        String str = g.toJson(intent.getSerializableExtra("annonce"));
        Annonce annonce = g.fromJson(str, Annonce.class);

        String str2 = g.toJson(intent.getSerializableExtra("client"));
        Client client = g.fromJson(str2, Client.class);

        String str3 = g.toJson(intent.getSerializableExtra("mission"));
        Mission mission = g.fromJson(str3, Mission.class);

        String serviceNom = intent.getStringExtra("serviceNom");
        String serviceType = intent.getStringExtra("serviceType");




        titleTextView.setText("Mission");
        descriptionTextView.setText(serviceNom + "\n" + serviceType + "\n" + annonce.getDescriptionAnnonce());
        detailTextView.setText(client.getNom() + "\n" + client.getPrenom() + "\n" + client.getEmail()
                + "\n" + annonce.getDetailAnnonce() + "\n" + client.getTel());
        horaireTextView.setText(annonce.getDebutDate() + "\n" + annonce.getDebutHeure());
        infoTextView.setText(mission.getInfoPrestataire());

        if(mission.getIsValide().equals("false")) {
            finaliserButton.setVisibility(View.GONE);
            annulerButton.setVisibility(View.VISIBLE);
            timerTextView.setVisibility(View.GONE);
        } else if(mission.getIsValide().equals("true") && mission.getInProcess().equals("false")){
            finaliserButton.setVisibility(View.VISIBLE);
            annulerButton.setVisibility(View.VISIBLE);
            timerTextView.setVisibility(View.GONE);
            finaliserButton.setText("Commencer");
            finaliserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetworkProvider.getInstance().missionChangeInProcess(mission.getIdMission(), "true", new NetworkProvider.Listener<List<Mission>>() {
                        @Override
                        public void onSuccess(List<Mission> data) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                            String dateToChange = dateFormat.format(Calendar.getInstance().getTime());
                            Log.d("data", dateToChange);
                            NetworkProvider.getInstance().missionChangeDebutHeure(mission.getIdMission(), dateToChange, new NetworkProvider.Listener<List<Mission>>() {
                                @Override
                                public void onSuccess(List<Mission> data) {
                                    Log.d("done", data.toString());
                                    finaliserButton.setText("Terminer");
                                    annulerButton.setVisibility(View.GONE);
                                    timerTextView.setVisibility(View.VISIBLE);
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                while (!isInterrupted()) {
                                                    Thread.sleep(1000);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                                            String dateChange = dateFormat.format(Calendar.getInstance().getTime());
                                                            try {
                                                                Date date1 = dateFormat.parse(data.get(0).getDebutHeure());
                                                                Date date2 = dateFormat.parse(dateChange);
                                                                long millis = 0;
                                                                if(date1.getTime() > date2.getTime()) {
                                                                    String timeChange = Integer.toString((24 - Integer.parseInt(data.get(0).getDebutHeure().split(":")[0])));
                                                                    date1 = dateFormat.parse(timeChange + ":" + Integer.parseInt(data.get(0).getDebutHeure().split(":")[1]));
                                                                    millis = date1.getTime() + date2.getTime();
                                                                } else {
                                                                    millis = date2.getTime() - date1.getTime();
                                                                }
                                                                int hours = (int) (millis/(1000 * 60 * 60));
                                                                int mins = (int) ((millis/(1000*60)) % 60);

                                                                String diff = String.format("%02d", hours) + "h" + String.format("%02d", mins);
                                                                Log.d("timeSetting", diff);
                                                                timerTextView.setText(diff);
                                                            } catch (ParseException p){
                                                                Log.d("timeSetting", p.getMessage());
                                                            }
                                                        }
                                                    });
                                                }
                                            } catch (InterruptedException e) {
                                            }
                                        }
                                    };
                                    thread.start();
                                }

                                @Override
                                public void onError(Throwable t) {

                                }
                            });

                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.d("done", t.getMessage());
                        }
                    });
                }
            });
            finaliserButton.setVisibility(View.VISIBLE);
            annulerButton.setVisibility(View.VISIBLE);

        } else if(mission.getIsValide().equals("true") && mission.getInProcess().equals("true")) {
            finaliserButton.setVisibility(View.VISIBLE);
            annulerButton.setVisibility(View.GONE);
            timerTextView.setVisibility(View.VISIBLE);
            finaliserButton.setText("Terminer");
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                    String dateChange = dateFormat.format(Calendar.getInstance().getTime());
                                    try {
                                        Date date1 = dateFormat.parse(mission.getDebutHeure());
                                        Date date2 = dateFormat.parse(dateChange);
                                        long millis = 0;
                                        if(date1.getTime() > date2.getTime()) {
                                            String timeChange = Integer.toString((24 - Integer.parseInt(mission.getDebutHeure().split(":")[0])));
                                            date1 = dateFormat.parse(timeChange + ":" + Integer.parseInt(mission.getDebutHeure().split(":")[1]));
                                            millis = date1.getTime() + date2.getTime();
                                        } else {
                                            millis = date2.getTime() - date1.getTime();
                                        }
                                        int hours = (int) (millis/(1000 * 60 * 60));
                                        int mins = (int) ((millis/(1000*60)) % 60);

                                        String diff = String.format("%02d", hours) + ":" + String.format("%02d", mins);
                                        Log.d("timeSetting", diff);
                                        timerTextView.setText(diff);
                                    } catch (ParseException p){
                                        Log.d("timeSetting", p.getMessage());
                                    }
                                }
                            });
                        }
                    } catch (InterruptedException e) {

                    }
                }
            };
            thread.start();

            finaliserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("timeShow", timerTextView.getText().toString());
                    thread.interrupt();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    String hourFinal = dateFormat.format(Calendar.getInstance().getTime());
                    NetworkProvider.getInstance().missionSetFinHeure(mission.getIdMission(), hourFinal, timerTextView.getText().toString(), new NetworkProvider.Listener<List<Mission>>() {
                        @Override
                        public void onSuccess(List<Mission> data) {
                            timerTextView.setText("Mission Terminé\nVous recevrez votre revenu quand vous serez payé");
                        }

                        @Override
                        public void onError(Throwable t) {

                        }
                    });
                }
            });
        }

    }
}
