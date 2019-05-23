package com.example.spareservice.data.dto.mapper;

import com.example.spareservice.data.dto.AnnonceDTO;
import com.example.spareservice.data.dto.ServiceDTO;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {

    public static List<Service> map(List<ServiceDTO> annonceDTOList) {
        List<Service> annonceList = new ArrayList<>();
        for (ServiceDTO annonceDTO : annonceDTOList) {
            annonceList.add(map(annonceDTO));
        }
        return annonceList;
    }

    private static Service map(ServiceDTO annonceDTO) {
        Service annonce = new Service();

        annonce.setNomService(annonceDTO.getNomService());
        annonce.setTypeService(annonceDTO.getTypeService());
        return annonce;
    }
}
