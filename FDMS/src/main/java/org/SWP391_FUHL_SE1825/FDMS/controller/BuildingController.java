package org.SWP391_FUHL_SE1825.FDMS.controller;


import org.SWP391_FUHL_SE1825.FDMS.dto.request.BuildingCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BuildingListResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Building;
import org.SWP391_FUHL_SE1825.FDMS.entity.Semester;
import org.SWP391_FUHL_SE1825.FDMS.service.BuildingService;
import org.SWP391_FUHL_SE1825.FDMS.service.ISemesterService;
import org.SWP391_FUHL_SE1825.FDMS.service.Impl.BuildingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/building")
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    private final BuildingService buildingService;
    private final ISemesterService iSemesterService;

    @Autowired
    public BuildingController(BuildingServiceImpl buildingService, ISemesterService iSemesterService) {
        this.buildingService = buildingService;
        this.iSemesterService = iSemesterService;
    }

    @GetMapping("")
    public String getBuildings(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Page<BuildingListResponse> buildings;
        buildings = buildingService.listBuilding(page);
        model.addAttribute("buildings", buildings.getContent());
        model.addAttribute("totalPages", buildings.getTotalPages());
        model.addAttribute("currentPage", page);
        return "buildings";
    }

    @PostMapping("/add")
    public ResponseEntity<String> createBuilding(@RequestBody BuildingCreationRequest request) {
        try {
            Building existbuilding = buildingService.existedBuilding(request.getBuildingName());
            if (existbuilding != null) {
                return ResponseEntity.badRequest().body("Building with name " + request.getBuildingName() + " already exists");
            }
            if (request.getNumberFloor() <= 0) {
                return ResponseEntity.badRequest().body("Number of floors must be greater than 0");
            }

            if (request.getNumberRoom() <= 0) {
                return ResponseEntity.badRequest().body("Number of rooms per floor must be greater than 0");
            }
            buildingService.createRequest(request);
            return ResponseEntity.ok("Add new building successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to add new building");
        }
    }

    @PostMapping("/lock/{id}")
    public ResponseEntity<String> lockBuilding(@PathVariable long id) {
        try {
            Semester semester = iSemesterService.getSemester();
            if (buildingService.isStudentStayinBuilding(semester.getId(), id)) {
                buildingService.lockBuilding(id);
                return ResponseEntity.ok("Lock Building successfully");
            }
            return ResponseEntity.badRequest().body("Student still stay in building");
        } catch (Exception e) {
            logger.error("Failed to lock building with id: " + id, e);
            return ResponseEntity.badRequest().body("Failed to lock building");
        }

    }

    @PostMapping("/unlock/{id}")
    public ResponseEntity<String> unlockBuilding(@PathVariable long id) {
        try {
            buildingService.unlockBuilidng(id);

            return ResponseEntity.ok("Lock Building successfully");
        } catch (Exception e) {
            logger.error("Failed to unlock building with id: " + id, e);
            return ResponseEntity.badRequest().body("Fail to unlock building");
        }
    }
}