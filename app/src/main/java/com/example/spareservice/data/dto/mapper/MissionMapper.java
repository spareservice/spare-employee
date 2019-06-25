package com.example.spareservice.data.dto.mapper;

import com.example.spareservice.data.dto.ClientDTO;
import com.example.spareservice.data.dto.MissionDTO;
import com.example.spareservice.data.model.Client;
import com.example.spareservice.data.model.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionMapper {
    public static List<Mission> map(List<MissionDTO> missionDTOList) {
        List<Mission> missionList = new ArrayList<>();
        for (MissionDTO missionDTO : missionDTOList) {
            missionList.add(map(missionDTO));
        }
        return missionList;
    }

    private static Mission map(MissionDTO missionDTO) {
        Mission mission = new Mission();

        mission.setIdAnnonce(missionDTO.getIdAnnonce());
        mission.setIdPrestataire(missionDTO.getIdPrestataire());
        mission.setIdClient(missionDTO.getIdClient());
        mission.setDebutDate(missionDTO.getDebutDate());
        mission.setDebutHeure(missionDTO.getDebutHeure());
        mission.setValide(missionDTO.isValide());
        mission.setInProcess(missionDTO.isInProcess());

        return mission;
    }
}
