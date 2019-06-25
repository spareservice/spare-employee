package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class ServiceDTO {

    @SerializedName("nomService") private String nomService;
    @SerializedName("typeService") private String typeService;

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "nomService='" + nomService + '\'' +
                ", typeService='" + typeService + '\'' +
                '}';
    }
}
