package com.example.spareservice.data.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.undercouch.bson4jackson.types.ObjectId;


public class AnnonceDTO {
    @SerializedName("_id") private String idAnnonce;
    @SerializedName("idClient") private String idClient;
    @SerializedName("idService") private String idService;
    @SerializedName("descriptionAnnonce") private String descriptionAnnonce;
    @SerializedName("detailAnnonce") private String detailAnnonce;

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public String getDetailAnnonce() {
        return detailAnnonce;
    }

    public void setDetailAnnonce(String detailAnnonce) {
        this.detailAnnonce = detailAnnonce;
    }

    @Override
    public String toString() {
        return "AnnonceDTO{" +
                "idAnnonce='" + idAnnonce + '\'' +
                ", idClient='" + idClient + '\'' +
                ", idService='" + idService + '\'' +
                ", descriptionAnnonce='" + descriptionAnnonce + '\'' +
                ", detailAnnonce='" + detailAnnonce + '\'' +
                '}';
    }
}
