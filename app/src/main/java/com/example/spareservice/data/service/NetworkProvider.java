package com.example.spareservice.data.service;

import android.util.Log;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.MissionDTO;
import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.dto.mapper.AnnonceMapper;
import com.example.spareservice.data.dto.mapper.ClientMapper;
import com.example.spareservice.data.dto.mapper.MissionMapper;
import com.example.spareservice.data.dto.mapper.PrestataireMapper;
import com.example.spareservice.data.dto.mapper.ServiceMapper;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Mission;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.model.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    MySpareService mySpareService;

    private static NetworkProvider instance;

    public static NetworkProvider getInstance() {
        if (instance == null) {
            instance = new NetworkProvider();
        }
        return instance;
    }

    private NetworkProvider() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mySpareService = retrofit.create(MySpareService.class);
    }

    public void allService(Listener<List<Service>> listener) {
        mySpareService.allService().enqueue(new Callback<List<ServiceDTO>>() {
            @Override public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
                List<ServiceDTO> annonceDTOList = response.body();
                List<Service> annonceList = ServiceMapper.map(annonceDTOList);

                listener.onSuccess(annonceList);
            }

            @Override public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void getAnnonces(Listener<List<Annonce>> listener) {
        mySpareService.getAnnonces().enqueue(new Callback<List<AnnonceDTO>>() {
            @Override public void onResponse(Call<List<AnnonceDTO>> call, Response<List<AnnonceDTO>> response) {
                List<AnnonceDTO> annonceDTOList = response.body();
                List<Annonce> annonceList = AnnonceMapper.map(annonceDTOList);

                listener.onSuccess(annonceList);
            }

            @Override public void onFailure(Call<List<AnnonceDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }


    public void getService(String id, Listener<List<Service>> listener) {
        mySpareService.getService(id).enqueue(new Callback<List<ServiceDTO>>() {
            @Override public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
                List<ServiceDTO> annonceDTOList = response.body();
                List<Service> annonceList = ServiceMapper.map(annonceDTOList);

                listener.onSuccess(annonceList);
            }

            @Override public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void getClient(String id, Listener<List<Client>> listener) {
        mySpareService.getClient(id).enqueue(new Callback<List<ClientDTO>>() {
            @Override public void onResponse(Call<List<ClientDTO>> call, Response<List<ClientDTO>> response) {
                List<ClientDTO> annonceDTOList = response.body();
                List<Client> annonceList = ClientMapper.map(annonceDTOList);

                listener.onSuccess(annonceList);
            }

            @Override public void onFailure(Call<List<ClientDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void addPrestataire(String nom, String prenom, String email, String mdp, String tel, String adresse, String cp, String ville, String service, Listener<List<Prestataire>> listener) {
        mySpareService.ajoutPrestataire(nom, prenom, email, mdp, tel, adresse, cp, ville, service).enqueue(new Callback<List<PrestataireDTO>>() {
            @Override
            public void onResponse(Call<List<PrestataireDTO>> call, Response<List<PrestataireDTO>> response) {
                List<PrestataireDTO> prestataireDTOList = response.body();
                List<Prestataire> prestataireList = PrestataireMapper.map(prestataireDTOList);

                listener.onSuccess(prestataireList);
            }

            @Override
            public void onFailure(Call<List<PrestataireDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void addMission(String idAnnonce, String idPrestataire, String idClient, String debutDate, String debutHeure, String infoPrestataire, boolean isValide, boolean inProcess, Listener<List<Mission>> listener) {
        mySpareService.ajoutMission(idAnnonce, idPrestataire, idClient, debutDate, debutHeure, infoPrestataire, isValide, inProcess).enqueue(new Callback<List<MissionDTO>>() {
            @Override
            public void onResponse(Call<List<MissionDTO>> call, Response<List<MissionDTO>> response) {
                List<MissionDTO> missionDTOList = response.body();
                List<Mission> missionList = MissionMapper.map(missionDTOList);

                listener.onSuccess(missionList);
            }

            @Override
            public void onFailure(Call<List<MissionDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void connexionPrestataire(String email, String mdp, Listener<List<Prestataire>> listener){
        mySpareService.connexionPrestataire(email, mdp).enqueue(new Callback<List<PrestataireDTO>>() {
            @Override
            public void onResponse(Call<List<PrestataireDTO>> call, Response<List<PrestataireDTO>> response) {
                List<PrestataireDTO> prestataireDTOList = response.body();
                List<Prestataire> prestataireList = PrestataireMapper.map(prestataireDTOList);

                listener.onSuccess(prestataireList);
            }

            @Override
            public void onFailure(Call<List<PrestataireDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }






    public interface Listener<T> {
        void onSuccess(T data);

        void onError(Throwable t);
    }
}
