package com.example.spareservice.views;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class AccueilActivity extends AppCompatActivity {



    /*
    ListView listView;
    */
    @BindView(R.id.activity_choose_annonce_rcv)
    RecyclerView annonceChoiceRcv;
    @BindView(R.id.activity_accueil_logo_image)
    ImageView imageView;

    private AnnonceAdapter annonceAdapter;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Glide.with(AccueilActivity.this).load(R.drawable.spareservicelogomini).into(imageView);

        Intent intent = getIntent();

        String idPrestataire = intent.getStringExtra("idPrestataire");

        loadData();
        initRecyclerView(idPrestataire);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadData() {
        NetworkProvider.getInstance().getAnnonces(new NetworkProvider.Listener<List<Annonce>>() {
            @Override public void onSuccess(List<Annonce> data) {
                Log.d("AccueilActivity", data.toString());
                annonceAdapter.setWeaponList(data);
            }

            @Override public void onError(Throwable t) {

            }
        });


    }

    private void initRecyclerView(String idPrestataire) {
        annonceChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
        annonceAdapter = new AnnonceAdapter();
        annonceChoiceRcv.setAdapter(annonceAdapter);

        annonceAdapter.setItemClickListener(annonce -> {
            Toast.makeText(AccueilActivity.this, annonce.getDetailAnnonce(), Toast.LENGTH_SHORT).show();
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
                                Client client = new Client();
                                client.setIdClient(data.get(0).getIdClient());
                                client.setNom(data.get(0).getNom());
                                client.setPrenom(data.get(0).getPrenom());
                                client.setEmail(data.get(0).getEmail());
                                client.setTel(data.get(0).getTel());
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


}
