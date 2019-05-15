package com.example.spareservice.data.service;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.mapper.AnnonceMapper;
import com.example.spareservice.data.model.Annonce;

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
                List<AnnonceDTO> weaponDTOList = response.body();
                List<Annonce> weaponList = AnnonceMapper.map(weaponDTOList);

                listener.onSuccess(weaponList);
            }

            @Override public void onFailure(Call<List<AnnonceDTO>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public interface Listener<T> {
        void onSuccess(T data);

        void onError(Throwable t);
    }
}
