package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.mapper.ClientMapper;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Service;
import com.example.spareservice.data.service.NetworkProvider;

import java.io.Serializable;
import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ButterKnife.bind(this);

        /*
        listView = (ListView)findViewById(R.id.listview);
        imageView = findViewById(R.id.activity_accueil_logo_image);
        */

        Glide.with(AccueilActivity.this).load(R.drawable.spareservicelogomini).into(imageView);

        /*
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Android");
        arrayList.add("is");
        arrayList.add("great");
        arrayList.add("and I love it");
        arrayList.add("It");
        arrayList.add("is");
        arrayList.add("better");
        arrayList.add("then");
        arrayList.add("many");
        arrayList.add("other");
        arrayList.add("operating systems.");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);
        */

        loadData();
        initRecyclerView();
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

    private void initRecyclerView() {
        annonceChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
        annonceAdapter = new AnnonceAdapter();
        annonceChoiceRcv.setAdapter(annonceAdapter);

        annonceAdapter.setItemClickListener(new AnnonceAdapter.ItemClickListener() {

            @Override
            public void onClick(Annonce weapon) {
                Toast.makeText(AccueilActivity.this, weapon.getDetailAnnonce(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccueilActivity.this, AnnonceActivity.class);
                    intent.putExtra("annonce", weapon);
                    NetworkProvider.getInstance().getService(weapon.getIdService(), new NetworkProvider.Listener<List<Service>>() {
                        @Override
                        public void onSuccess(List<Service> data) {
                            intent.putExtra("annonce", weapon);
                            intent.putExtra("serviceNom", data.get(0).getNomService());
                            intent.putExtra("serviceType", data.get(0).getTypeService());
                            NetworkProvider.getInstance().getClient(weapon.getIdClient(), new NetworkProvider.Listener<List<Client>>() {
                                @Override
                                public void onSuccess(List<Client> data) {
                                    Client client = new Client();
                                    client.setNom(data.get(0).getNom());
                                    client.setPrenom(data.get(0).getPrenom());
                                    client.setEmail(data.get(0).getEmail());
                                    client.setTel(data.get(0).getTel());
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
        });
    }


}
