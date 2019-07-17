package com.example.spareservice.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.spareservice.data.model.Mission;
import com.example.spareservice.data.model.Service;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MissionActivity extends MenuActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_choose_mission_rcv)
    RecyclerView missionChoiceRcv;
    @BindView(R.id.activity_mission_logo_image)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private MissionAdapter missionAdapter;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        ButterKnife.bind(this);

        prefs = this.getSharedPreferences("preferences", MODE_PRIVATE);

        Glide.with(MissionActivity.this).load(R.drawable.spareservicelogomini).into(imageView);

        String idPresta = prefs.getString("idPrestataire", "none");

        initNavigationView();
        initRecyclerView(idPresta);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = this.getSharedPreferences("preferences", MODE_PRIVATE);
        String idPresta = prefs.getString("idPrestataire", "none");
        NetworkProvider.getInstance().getAllMission(idPresta, new NetworkProvider.Listener<List<Mission>>() {
            @Override public void onSuccess(List<Mission> data) {
                missionAdapter.setWeaponList(data);
                Log.d("charge", "1");
            }
            @Override public void onError(Throwable t) {}
        });
    }

    private void initRecyclerView(String idPrestataire) {
        missionChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
        missionAdapter = new MissionAdapter();
        missionChoiceRcv.setAdapter(missionAdapter);

        missionAdapter.setItemClickListener(mission -> {
            Intent intent = new Intent(MissionActivity.this, MissionDetailActivity.class);
            intent.putExtra("mission", mission);
            NetworkProvider.getInstance().getAnnonceById(mission.getIdAnnonce(), new NetworkProvider.Listener<List<Annonce>>() {
                @Override
                public void onSuccess(List<Annonce> data) {
                    intent.putExtra("annonce", data.get(0));
                    NetworkProvider.getInstance().getService(data.get(0).getIdService(), new NetworkProvider.Listener<List<Service>>() {
                        @Override
                        public void onSuccess(List<Service> data) {
                            intent.putExtra("serviceNom", data.get(0).getNomService());
                            intent.putExtra("serviceType", data.get(0).getTypeService());
                            NetworkProvider.getInstance().getClient(mission.getIdClient(), new NetworkProvider.Listener<List<Client>>() {
                                @Override
                                public void onSuccess(List<Client> data) {
                                    Client client = new Client(data.get(0).getIdClient(), data.get(0).getNom(),
                                            data.get(0).getPrenom(), data.get(0).getEmail(), data.get(0).getTel());
                                    intent.putExtra("client", client);
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
                }

                @Override
                public void onError(Throwable t) {

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
