package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class PrestataireDTO {
    @SerializedName("nom") private String nom;
    @SerializedName("prenom") private String prenom;
    @SerializedName("email") private String email;
    @SerializedName("mdp") private String mdp;
    @SerializedName("tel") private String tel;
    @SerializedName("salaire") private String salaire;
}
