package org.SWP391_FUHL_SE1825.FDMS.controller;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.SWP391_FUHL_SE1825.FDMS.repository.BuildingRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.RoomRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    private final IRoomService roomService;
    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;

    @GetMapping("")
    public String search(@RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "name", required = false, defaultValue = "") String name,
                         @RequestParam(value = "buildingId", required = false, defaultValue = "-1") Integer buildingId,
                         Model model) {
        Page<Room> roomPage = roomService.list(page, name, buildingId);
        model.addAttribute("rooms", roomPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("buildingId", buildingId);
        model.addAttribute("buildings", buildingRepository.findAllBuildings());
        model.addAttribute("Allbuildings", buildingRepository.findAll());
        model.addAttribute("totalPages", roomPage.getTotalPages());
        return "rooms";
    }

    @PostMapping("/add")
    public String add(@RequestParam(value = "buildingId", required = true) Integer buildingId,
                      @RequestParam(value = "floor", required = true) Integer floor,
                      @RequestParam(value = "numRoom", required = false, defaultValue = "14") Integer numRoom,
                      @RequestParam(value = "roomType", required = true) String roomType) {

        roomService.add(buildingId, floor, roomType, numRoom);
        return "redirect:/room";
    }
    @GetMapping("/edit")
    public String edit() {
        return "edit_room";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "roomId", required = true) Integer roomId) {
        roomRepository.deleteById(roomId);
        return "redirect:/room";
    }
}
