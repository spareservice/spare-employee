package com.example.spareservice.data.service;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.model.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MySpareService {
    @GET("getAnnonces")
    Call<List<AnnonceDTO>> getAnnonces();

    @GET("getService/{id}")
    Call<List<ServiceDTO>> getService(@Path("id") String idService);

    @GET("getClient/{id}")
    Call<List<ClientDTO>> getClient(@Path("id") String idClient);
}
