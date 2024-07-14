package org.SWP391_FUHL_SE1825.FDMS.controller;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BedBookedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.*;
import org.SWP391_FUHL_SE1825.FDMS.repository.*;
import org.SWP391_FUHL_SE1825.FDMS.utils.CommonUtils;
import org.SWP391_FUHL_SE1825.FDMS.security.SecurityUtil;
import org.SWP391_FUHL_SE1825.FDMS.service.IRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class BookingController {

    private final IRequestService iRequestService;
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;
    private final BookedBedRepository bookedBedRepository;

    @GetMapping("/booking")
    public String book(@RequestParam(value = "roomId", required = false) Long  roomId,
                       @RequestParam(value = "bedId", required = false) Long  bedId,
                       Model model) {

        if (roomId == null && bedId != null) {
            model.addAttribute("bedId", bedId);
            roomId = roomRepository.findRoomIdByBedId(bedId);
        }

        Room roomOptional = roomRepository.findByRoomId(roomId);
        List<Bed> beds = bedRepository.findBedBooking(roomId);
        List<Semester> semesters = semesterRepository.findAll();
        if (model.containsAttribute("error")) {
            model.addAttribute("error", model.getAttribute("error"));
        }

        model.addAttribute("room", roomOptional);
        model.addAttribute("beds", beds);
        model.addAttribute("semesters", semesters);
        return "booking_bed";
    }

    @PostMapping("/booking")
    public String booking(@RequestParam(value = "roomId", required = false) Long roomId,
                          @RequestParam(value = "bedId", required = false) Long bedId,
                          @RequestParam(value = "semesterId", required = false) Long semester,
                          RedirectAttributes redirectAttributes) {

        Room roomOptional = roomRepository.findByRoomId(roomId);
        int typeId = 0;
        if (roomOptional.getRoom_type_desciption().equals("phòng 3 người")) {
            typeId = 11;
        } else if (roomOptional.getRoom_type_desciption().equals("phòng 4 người")) {
            typeId = 12;
        } else if (roomOptional.getRoom_type_desciption().equals("phòng 6 người")) {
            typeId = 10;
        }

        String currentUserName = SecurityUtil.getSessionUser();
        StudentSecondaryInformation studentSecondaryInformation = studentRepository.findByUsername(currentUserName);
        BedBooked bedBooked1 = bookedBedRepository.checkExist(studentSecondaryInformation.getId());

        if (bedBooked1 != null) {
            redirectAttributes.addFlashAttribute("error", "You are not allowed to book this bed.");
            return "redirect:/booking?roomId=" + roomId + "&bedId=" + bedId;
        }

        Bed bed = bedRepository.findBedById(bedId);

        long id = CommonUtils.generateUniqueId();
        BedBooked bedBooked = BedBooked.builder()
                .id(id)
                .studentId(studentSecondaryInformation.getId())
                .bedId(bedId)
                .semesterId(semester)
                .status("pending")
                .build();

        bookedBedRepository.save(bedBooked);
        bedRepository.updateStatus(bedId);

        RequestApplication request = RequestApplication.builder()
                .requestContent("Student with roll number " + studentSecondaryInformation.getRollNumber()
                        + " request to book bed " + bed.getBedName() + " at room " + roomOptional.getRoomName())
                .status("pending")
                .requestApplicationTypeId(typeId)
                .bedBookedId(id)
                .build();

        iRequestService.requestBooking(request);
        return "redirect:/home";
    }


    @GetMapping("/history")
    public String history(Model model) {

        String currentUserName = SecurityUtil.getSessionUser();
        StudentSecondaryInformation studentSecondaryInformation = studentRepository.findByUsername(currentUserName);
        List<BedBookedResponse> bedBookeds = bookedBedRepository.findByStudentId(studentSecondaryInformation.getId());
        model.addAttribute("bedBookeds", bedBookeds);

        return "resident_history";
    }

}