package com.example.spareservice.data.service;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.MissionDTO;
import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.model.Prestataire;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MySpareService {
    @GET("/getAnnonces")
    Call<List<AnnonceDTO>> getAnnonces();

    @GET("/allService")
    Call<List<ServiceDTO>> allService();

    @GET("/services/getService/{id}")
    Call<List<ServiceDTO>> getService(@Path("id") String idService);

    @GET("/getClient/{id}")
    Call<List<ClientDTO>> getClient(@Path("id") String idClient);

    @POST("/ajoutPrestataire")
    Call<List<PrestataireDTO>> ajoutPrestataire(@Query("nom") String nom,
                                                    @Query("prenom") String prenom,
                                                    @Query("email") String email,
                                                    @Query("mdp") String mdp,
                                                    @Query("tel") String tel,
                                                    @Query("adresse") String adresse,
                                                    @Query("cp") String cp,
                                                    @Query("ville") String ville);
    @GET("/connexionPrestataire")
    Call<List<PrestataireDTO>> connexionPrestataire(@Query("email") String email, @Query("mdp") String mdp);

    @POST("/ajoutMission")
    Call<List<MissionDTO>> ajoutMission(@Query("idAnnonce") String idAnnonce,
                                           @Query("idPrestataire") String idPrestataire,
                                           @Query("idClient") String idClient,
                                           @Query("debutDate") String debutDate,
                                           @Query("debutHeure") String debutHeure,
                                           @Query("infoPrestataire") String infoPrestataire,
                                           @Query("isValide") String isValide,
                                           @Query("inProcess") String inProcess);

    @GET("/mission/getAll")
    Call<List<MissionDTO>> getAllMission(@Query("idPrestataire") String idPrestataire);

    @GET("/getAnnonceById/{id}")
    Call<List<AnnonceDTO>> getAnnonceById(@Path("id") String idAnnonce);

    @GET("/getPrestataireById/{id}")
    Call<List<PrestataireDTO>> getPrestataireById(@Path("id") String idPrestataire);


    @PATCH("/missionChangeInProcess/{idMission}")
    Call<List<MissionDTO>> changeMissionInProcess(@Path("idMission") String idMission, @Query("inProcess") String inProcess);

    @PATCH("/missionChangeDebutHeure/{idMission}")
    Call<List<MissionDTO>> changeMissionDebutHeure(@Path("idMission") String idMission, @Query("debutHeure") String debutHeure);

    @PATCH("/missionSetFinHeure/{idMission}")
    Call<List<MissionDTO>> setMissionFinHeure(@Path("idMission") String idMission, @Query("finHeure") String finHeure, @Query("duree") String duree);
}
