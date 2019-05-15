package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class AnnonceDTO {
    @SerializedName("idClient") private String idClient;
    @SerializedName("idService") private String idService;
    @SerializedName("descriptionAnnonce") private String descriptionAnnonce;
    @SerializedName("detailAnnonce") private String detailAnnonce;

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
                "idClient='" + idClient + '\'' +
                ", isService='" + idService + '\'' +
                ", descriptionAnnonce='" + descriptionAnnonce + '\'' +
                ", detailPrestataire='" + detailAnnonce + '\'' +
                '}';
    }
}
