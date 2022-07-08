package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public Collection<Building> listBuildings() { //TODO oder hier Rückgabetypen immer Building oder so? @Jonathan
        return LogicUtils.convertBuildingsToBuildingResponse(buildingRepository.findAll());
    }

    public BuildingResponse getBuilding(String bin) {
        Optional<Building> buildingOptional = buildingRepository.findByBin(bin);
        return LogicUtils.convertBuildingToBuildingResponse(buildingOptional.get());
    }

    public BuildingResponse createBuilding(BuildingRequest buildingRequest) { //TODO hier wird halt wild hin und her geparsed was man sich theoretisch sparen könnte
        Building building = LogicUtils.convertBuildingRequestToBuilding(buildingRequest);
        String bin = buildingRepository.create(building);
        //TODO exceptions
        building.setId(bin);
        return LogicUtils.convertBuildingToBuildingResponse(building);
    }

    public void deleteBuilding(String bin) {
        //TODO exceptions je nach rückgabetyp
        buildingRepository.delete(bin);
    }

    public Building editBuilding(String bin, BuildingRequest buildingRequest) {
        Building building = LogicUtils.convertBuildingRequestToBuilding(buildingRequest);
        building.setId(bin);
        //TODO exceptions je nach rückgabetyp
        return buildingRepository.save(building);
    }

}
