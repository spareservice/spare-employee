package com.example.spareservice.views;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;

public class ProfilActivity extends MenuActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_profil_nom_textview)
    TextView nomTv;
    @BindView(R.id.activity_profil_email_textview)
    TextView emailTv;
    @BindView(R.id.activity_profil_tel_textview)
    TextView telTv;
    @BindView(R.id.activity_profil_adresse_textview)
    TextView adresseTv;
    @BindView(R.id.activity_profil_logo_imageview)
    ImageView logoIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Glide.with(ProfilActivity.this).load(R.drawable.spareservicelogomini).into(logoIv);

        prefs = this.getSharedPreferences("preferences", this.MODE_PRIVATE);
        String idPresta = prefs.getString("idPrestataire", "none");

        NetworkProvider.getInstance().getPrestataireById(idPresta, new NetworkProvider.Listener<List<Prestataire>>() {
            @Override
            public void onSuccess(List<Prestataire> data) {
                nomTv.setText(data.get(0).getNom() + "\n" + data.get(0).getPrenom());
                adresseTv.setText(data.get(0).getAdresse() + ",\n" + data.get(0).getCp() + " " + data.get(0).getVille());
                telTv.setText(data.get(0).getTel());
                emailTv.setText(data.get(0).getEmail());
            }

            @Override
            public void onError(Throwable t) {
                Log.d("errorProfil", t.getMessage());
            }
        });
    }

    private void initNavigationView(){
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}
