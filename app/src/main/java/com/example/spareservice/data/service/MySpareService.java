package com.example.spareservice.data.service;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.MissionDTO;
import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.model.Prestataire;
import com.example.spareservice.data.model.Service;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MySpareService {
    @GET("getAnnonces")
    Call<List<AnnonceDTO>> getAnnonces();

    @GET("allService")
    Call<List<ServiceDTO>> allService();

    @GET("getService/{id}")
    Call<List<ServiceDTO>> getService(@Path("id") String idService);

    @GET("getClient/{id}")
    Call<List<ClientDTO>> getClient(@Path("id") String idClient);

    @POST("/ajoutPrestataire")
    Call<List<PrestataireDTO>> ajoutPrestataire(@Query("nom") String nom,
                                                    @Query("prenom") String prenom,
                                                    @Query("email") String email,
                                                    @Query("mdp") String mdp,
                                                    @Query("tel") String tel,
                                                    @Query("adresse") String adresse,
                                                    @Query("cp") String cp,
                                                    @Query("ville") String ville,
                                                    @Query("service") String service);
    @GET("/connexionPrestataire")
    Call<List<PrestataireDTO>> connexionPrestataire(@Query("email") String email, @Query("mdp") String mdp);

    @POST("ajoutMission")
    Call<List<MissionDTO>> ajoutMission(@Query("idAnnonce") String idAnnonce,
                                           @Query("idPrestataire") String idPrestataire,
                                           @Query("idClient") String idClient,
                                           @Query("debutDate") String debutDate,
                                           @Query("debutHeure") String debutHeure,
                                           @Query("infoPrestataire") String infoPrestataire,
                                           @Query("isValide") boolean isValide,
                                           @Query("inProcess") boolean inProcess);
}
