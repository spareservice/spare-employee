package com.example.spareservice.data.dto.mapper;

import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    public static List<Client> map(List<ClientDTO> annonceDTOList) {
        List<Client> annonceList = new ArrayList<>();
        for (ClientDTO annonceDTO : annonceDTOList) {
            annonceList.add(map(annonceDTO));
        }
        return annonceList;
    }

    private static Client map(ClientDTO annonceDTO) {
        Client annonce = new Client();
        annonce.setIdClient(annonceDTO.getIdClient());
        annonce.setNom(annonceDTO.getNom());
        annonce.setPrenom(annonceDTO.getPrenom());
        annonce.setEmail(annonceDTO.getEmail());
        annonce.setTel(annonceDTO.getTel());
        return annonce;
    }
}
