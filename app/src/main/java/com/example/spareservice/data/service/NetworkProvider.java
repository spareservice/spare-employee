package com.example.spareservice.data.service;

import android.util.Log;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.dto.mapper.AnnonceMapper;
import com.example.spareservice.data.dto.mapper.ClientMapper;
import com.example.spareservice.data.dto.mapper.PrestataireMapper;
import com.example.spareservice.data.dto.mapper.ServiceMapper;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Client;
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://spare-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mySpareService = retrofit.create(MySpareService.class);
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






    public interface Listener<T> {
        void onSuccess(T data);

        void onError(Throwable t);
    }
}
