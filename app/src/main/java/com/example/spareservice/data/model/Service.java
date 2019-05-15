package com.example.spareservice.data.model;

public class Service {
    private String nomService;
    private String typeService;

    @Override
    public String toString() {
        return "Service{" +
                "nomService='" + nomService + '\'' +
                ", typeService='" + typeService + '\'' +
                '}';
    }

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
}
