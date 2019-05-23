package com.example.spareservice.data.dto.mapper;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.model.Annonce;

import java.util.ArrayList;
import java.util.List;

public class AnnonceMapper {
    public static List<Annonce> map(List<AnnonceDTO> annonceDTOList) {
        List<Annonce> annonceList = new ArrayList<>();
        for (AnnonceDTO annonceDTO : annonceDTOList) {
            annonceList.add(map(annonceDTO));
        }
        return annonceList;
    }

    private static Annonce map(AnnonceDTO annonceDTO) {
        Annonce annonce = new Annonce();

        annonce.setIdClient(annonceDTO.getIdClient());
        annonce.setIdService(annonceDTO.getIdService());
        annonce.setDescriptionAnnonce(annonceDTO.getDescriptionAnnonce());
        annonce.setDetailAnnonce(annonceDTO.getDetailAnnonce());
        return annonce;
    }
}
