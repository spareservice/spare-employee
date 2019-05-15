package com.example.spareservice.data.dto.mapper;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.model.Annonce;

import java.util.ArrayList;
import java.util.List;

public class AnnonceMapper {
    public static List<Annonce> map(List<AnnonceDTO> weaponDTOList) {
        List<Annonce> weaponList = new ArrayList<>();
        for (AnnonceDTO weaponDTO : weaponDTOList) {
            weaponList.add(map(weaponDTO));
        }
        return weaponList;
    }

    private static Annonce map(AnnonceDTO weaponDTO) {
        Annonce weapon = new Annonce();

        weapon.setIdClient(weaponDTO.getIdClient());
        weapon.setIdService(weaponDTO.getIdService());
        weapon.setDescriptionAnnonce(weaponDTO.getDescriptionAnnonce());
        weapon.setDetailAnnonce(weaponDTO.getDetailAnnonce());
        return weapon;
    }
}
