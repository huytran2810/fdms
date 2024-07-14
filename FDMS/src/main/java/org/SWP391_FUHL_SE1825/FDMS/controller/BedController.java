package org.SWP391_FUHL_SE1825.FDMS.controller;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.AvailableBedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.SWP391_FUHL_SE1825.FDMS.service.IBedService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bed")
@AllArgsConstructor
public class BedController {
    private final IBedService bedService;

    @GetMapping("")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "status", required = false) String status,
                       @RequestParam(value = "roomId", required = false) Integer  roomId,
                       Model model) {
        Page<Bed> bedPage = bedService.list(page, status, roomId);
        model.addAttribute("beds", bedPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bedPage.getTotalPages());
        return "beds";
    }

    @GetMapping("/available")
    public String listAvailable(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "status", required = false) String status,
                                @RequestParam(value = "roomId", required = false) Integer  roomId,
                                Model model) {
        Page<AvailableBedResponse> bedPage = bedService.availableList(page, status, roomId);
        model.addAttribute("beds", bedPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bedPage.getTotalPages());
        return "available_beds";
    }


    @GetMapping("/add")
    public String add() {
        return "add_room";
    }
    @GetMapping("/detail")
    public String detail() {
        return "studentEW";
    }
    @GetMapping("/adminEWStudent")
    public String adminEWStudent() {
        return "adminEW_student";
    }
    @GetMapping("/edit")
    public String edit() {
        return "edit_room";
    }

}
