package com.example.spareservice.data.dto.mapper;


import com.example.spareservice.data.dto.PrestataireDTO;
import com.example.spareservice.data.model.Prestataire;

import java.util.ArrayList;
import java.util.List;

public class PrestataireMapper {

    public static List<Prestataire> map(List<PrestataireDTO> annonceDTOList) {
        List<Prestataire> annonceList = new ArrayList<>();
        for (PrestataireDTO annonceDTO : annonceDTOList) {
            annonceList.add(map(annonceDTO));
        }
        return annonceList;
    }

    private static Prestataire map(PrestataireDTO annonceDTO) {
        Prestataire annonce = new Prestataire();

        annonce.setNom(annonceDTO.getNom());
        annonce.setPrenom(annonceDTO.getPrenom());
        annonce.setEmail(annonceDTO.getEmail());
        annonce.setTel(annonceDTO.getTel());
        return annonce;
    }
}
