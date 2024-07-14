package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.request.BuildingCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BuildingListResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {
    List<Building> findAll();
    Page<Building> list(int index);
    List<Building> findbyBuildingName(String name);
    Building createRequest(BuildingCreationRequest request);
    void deleteBuilding(long id);
    Page<BuildingListResponse> listBuilding(int page);
    void lockBuilding(long id);
    void unlockBuilidng(long id);
    Building existedBuilding(String buildingname);
    boolean isStudentStayinBuilding(long semester_id,long building_id);

}
