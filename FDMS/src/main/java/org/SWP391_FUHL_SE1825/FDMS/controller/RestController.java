package org.SWP391_FUHL_SE1825.FDMS.controller;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.ManagerAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.StudentAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RoomDetailResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;
import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {

    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final StudentRepository studentRepository;
    private final ManagerRepository managerRepository;

    @GetMapping("/room/all")
    public List<Room> all() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/details")
    public List<RoomDetailResponse> getRoomDetails(@RequestParam("roomId") Integer roomId) {
        return roomRepository.getRoomDetails(roomId);
    }

    @GetMapping("/request/get_price")
    public float price(@RequestParam long id) {
        RequestApplicationType requestApplicationType = requestTypeRepository.findById(id);
        return requestApplicationType.getAmount();
    }

    @GetMapping("/building/floors")
    public List<Integer> getFloor(@RequestParam("buildingId") Long buildingId) {
        return buildingRepository.findAllBuilding(buildingId);
    }
    @GetMapping("/request/search/student")
    public List<String> StudentName(@RequestParam String managerStudentId){
        return studentRepository.searchStudentName(managerStudentId, PageRequest.of(0, 3));
    }

    @GetMapping("/admin/search/studentAJ")
    public List<StudentAJDTO> StudentNameRole(@RequestParam String managerStudentId) {
        return studentRepository.searchStudentAJ(managerStudentId, PageRequest.of(0, 3));
    }
    @GetMapping("/admin/search/managerAJ")
    public List<ManagerAJDTO> ManagerName(@RequestParam String managerStudentId) {
        List<ManagerAJDTO> results = managerRepository.searchManagerAJ(managerStudentId, PageRequest.of(0, 3));
        return results;
    }
}
