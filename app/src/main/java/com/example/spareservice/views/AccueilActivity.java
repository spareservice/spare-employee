package com.example.spareservice.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.model.Service;
import com.example.spareservice.data.service.NetworkProvider;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccueilActivity extends MenuActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_choose_annonce_rcv)
    RecyclerView annonceChoiceRcv;
    @BindView(R.id.activity_accueil_logo_image)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private AnnonceAdapter annonceAdapter;

    private SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ButterKnife.bind(this);

        prefs = this.getSharedPreferences("preferences", this.MODE_PRIVATE);

        Glide.with(AccueilActivity.this).load(R.drawable.spareservicelogomini).into(imageView);

        String idPresta = prefs.getString("idPrestataire", "none");

        initNavigationView();
        loadData();
        initRecyclerView(idPresta);
    }

    private void loadData() {
        NetworkProvider.getInstance().getAnnonces(new NetworkProvider.Listener<List<Annonce>>() {
            @Override public void onSuccess(List<Annonce> data) {
                Log.d("AccueilActivity", data.toString());
                annonceAdapter.setWeaponList(data);
            }
            @Override public void onError(Throwable t) {}
        });
    }

    private void initRecyclerView(String idPrestataire) {
        annonceChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
        annonceAdapter = new AnnonceAdapter();
        annonceChoiceRcv.setAdapter(annonceAdapter);

        annonceAdapter.setItemClickListener(annonce -> {
                Intent intent = new Intent(AccueilActivity.this, AnnonceActivity.class);
                intent.putExtra("annonce", annonce);
                NetworkProvider.getInstance().getService(annonce.getIdService(), new NetworkProvider.Listener<List<Service>>() {
                    @Override
                    public void onSuccess(List<Service> data) {
                        intent.putExtra("annonce", annonce);
                        intent.putExtra("serviceNom", data.get(0).getNomService());
                        intent.putExtra("serviceType", data.get(0).getTypeService());
                        NetworkProvider.getInstance().getClient(annonce.getIdClient(), new NetworkProvider.Listener<List<Client>>() {
                            @Override
                            public void onSuccess(List<Client> data) {
                                Client client = new Client(data.get(0).getIdClient(), data.get(0).getNom(),
                                        data.get(0).getPrenom(), data.get(0).getEmail(), data.get(0).getTel());
                                intent.putExtra("client", client);
                                intent.putExtra("idPrestataire", idPrestataire);
                                startActivity(intent);
                                onPause();
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d("erreur", t.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("erreur", t.getMessage());
                    }
                });
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
