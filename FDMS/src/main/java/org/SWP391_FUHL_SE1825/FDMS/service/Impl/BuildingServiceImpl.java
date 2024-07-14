package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.dto.request.BuildingCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BuildingListResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Building;
import org.SWP391_FUHL_SE1825.FDMS.repository.BuildingRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.BuildingService;
import org.SWP391_FUHL_SE1825.FDMS.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Page<Building> list(int index) {
        Pageable paging = PageRequest.of(index, 10);
        return buildingRepository.findAll(paging);
    }

    @Override
    public List<Building> findbyBuildingName(String name) {
        return buildingRepository.findAllByBuildingname(name);
    }

    @Override
    public Building createRequest(BuildingCreationRequest request) {
        return buildingRepository.save(Building.builder().id(CommonUtils.generateUniqueId()).buildingname(request.getBuildingName()).numberFloor(request.getNumberFloor()).numberRoomOfFloor(request.getNumberRoom()).status("available").build());
    }


    @Override
    public void deleteBuilding(long id) {
        Building building = buildingRepository.findBuildingById(id);
        buildingRepository.delete(building);
    }

    @Override
    public Page<BuildingListResponse> listBuilding(int page) {
        Pageable paging = PageRequest.of(page, 10);
        return buildingRepository.findBuildingRoomCounts(paging);
    }

    @Override
    public void lockBuilding(long id) {
        Building building = buildingRepository.findBuildingById(id);
        building.setStatus("Lock");
        buildingRepository.save(building);
    }

    @Override
    public void unlockBuilidng(long id) {
        Building building = buildingRepository.findBuildingById(id);
        building.setStatus("Available");
        buildingRepository.save(building);
    }

    @Override
    public Building existedBuilding(String buildingname) {
        return buildingRepository.findBuildingByBuildingname(buildingname);
    }

    @Override
    public boolean isStudentStayinBuilding(long semester_id, long building_id) {
        List<String> list = buildingRepository.getRoomByStudent(semester_id,building_id);
        return list != null;
    }


}
