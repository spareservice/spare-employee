package com.example.spareservice.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.model.Service;
import com.example.spareservice.data.service.NetworkProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscriptionActivity extends AppCompatActivity {

    @BindView(R.id.activity_inscription_logo_image)
    ImageView logo;
    @BindView(R.id.activity_inscription_subTitle)
    TextView subtitleTextView;
    @BindView(R.id.activity_inscription_nom_edt)
    EditText nomEditText;
    @BindView(R.id.activity_inscription_prenom_edt)
    EditText prenomEditText;
    @BindView(R.id.activity_inscription_email_edt)
    EditText emailEditText;
    @BindView(R.id.activity_inscription_mdp_edt)
    EditText mdpEditText;
    @BindView(R.id.activity_inscription_confMdp_edt)
    EditText confMdpEditText;
    @BindView(R.id.activity_inscription_tel_edt)
    EditText telEditText;
    @BindView(R.id.activity_inscription_adresse_edt)
    EditText adresseTextView;
    @BindView(R.id.activity_inscription_cp_edt)
    EditText cpTextView;
    @BindView(R.id.activity_inscription_ville_edt)
    EditText villeTextView;
    @BindView(R.id.activity_inscription_service_actv)
    MultiAutoCompleteTextView serviceMactv;
    @BindView(R.id.activity_connexion_inscrire_btn)
    Button inscrireBtn;

    public static final ArrayList<String> servicesList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ButterKnife.bind(this);

        Glide.with(InscriptionActivity.this).load(R.drawable.spareservicelogomini).into(logo);

        NetworkProvider.getInstance().allService(new NetworkProvider.Listener<List<Service>>() {
            @Override
            public void onSuccess(List<Service> data) {
                for(Service s : data) {
                    servicesList.add(s.getNomService());
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.d("errorGettingAllService", t.getMessage());
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(InscriptionActivity.this, R.layout.simple_list_item_1, servicesList);
        serviceMactv.setAdapter(adapter);
        serviceMactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        inscrireBtn.setOnClickListener(v -> {
            Intent intent = new Intent(InscriptionActivity.this, ConnexionActivity.class);

            String services = serviceMactv.getText().toString();


            if (mdpEditText.getText().toString().equals(confMdpEditText.getText().toString())){

                NetworkProvider.getInstance().addPrestataire(nomEditText.getText().toString(), prenomEditText.getText().toString(),
                        emailEditText.getText().toString(), mdpEditText.getText().toString(),
                        telEditText.getText().toString(), adresseTextView.getText().toString(),
                        cpTextView.getText().toString(), villeTextView.getText().toString(),
                        services, new NetworkProvider.Listener<List<Prestataire>>() {
                    @Override
                    public void onSuccess(List<Prestataire> data) {
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("errorAddingPrestataire", t.getMessage());
                    }
                });
            } else {
                subtitleTextView.setText("Le mot de passe n'est pas identique");
                subtitleTextView.setTextColor(getResources().getColor(R.color.colorError));
            }

        });
    }
}
