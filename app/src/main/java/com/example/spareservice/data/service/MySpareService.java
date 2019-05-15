package com.example.spareservice.data.service;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.PrestataireDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MySpareService {
    @GET("getAnnonces")
    Call<List<AnnonceDTO>> getAnnonces();
}
