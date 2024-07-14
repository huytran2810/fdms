package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.dto.EWDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.StudentAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.StudentEWBillDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWSemesterResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RoomStudentList;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.StudentEWBill;
import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;
import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.SWP391_FUHL_SE1825.FDMS.entity.Semester;
import org.SWP391_FUHL_SE1825.FDMS.repository.EWRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.RoomRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.SemesterRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.StudentRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IEWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class EWController {
    private final RoomRepository roomRepository;
    private final SemesterRepository semesterRepository;
    private static final Logger logger = LoggerFactory.getLogger(EWController.class);

    @Autowired
    IEWService ewService;
    @Autowired
    EWRepository ewRepository;
    @Autowired
    private StudentRepository studentRepository;

    public EWController(RoomRepository roomRepository, SemesterRepository semesterRepository) {
        this.roomRepository = roomRepository;
        this.semesterRepository = semesterRepository;
    }

    @GetMapping("/ewadmin/{roomId}/detail")
    public String ewadmin(Model model,@PathVariable("roomId") Long roomId){

        //Manager Room Details
        String semester = getSemester();
        LocalDate date = LocalDate.now().minusDays(7);
        int monthValue = date.getMonthValue();

        Semester currentSemester = semesterRepository.findSemesterBySemesterName(semester);

        EWSemesterResponse ewrs = ewRepository.getRoomSemester(semester);

        EWDTO ew = new EWDTO();
        EWResponse ewResponseOld = ewRepository.getEW(date.getYear(),monthValue - 1,roomId);
        EWResponse ewResponse = ewRepository.getEW(date.getYear(),monthValue,roomId);

        if (ewResponse != null){
            ew.setRoomId(ewResponse.getRoomId());
            ew.setSemesterId(ewResponse.getSemesterId());
            ew.setSemesterName(semester);
            ew.setMonth(ewResponse.getMonth());
            ew.setYear(ewResponse.getYear());
            ew.setElectric(ewResponse.getElectricNew());
            ew.setWater(ewResponse.getWaterNew());

        }
        model.addAttribute("ewGetOldDetail", ewResponseOld);
        List<StudentEWBill> usedList = ewRepository.getRoomUsedList(roomId, semester, currentSemester.getId());

        ew.setRoomId(roomId);
        ew.setSemesterId(ewrs.getSemesterId());
        ew.setSemesterName(ewrs.getSemesterName());

        model.addAttribute("usedList", usedList);
        model.addAttribute("monthValue", monthValue);
        model.addAttribute("ew",ew);

        //Admin Room Details
        List<Semester> semesterList = semesterRepository.findAll();
        String currentSemesterName = getSemester();

        List<RoomStudentList> studentList = studentRepository.getRoomStudentList(roomId,semester);

        List<Map<String, Object>> billResults = ewRepository.getBillRoom(roomId, currentSemester.getId(),currentSemester.getId());

        // Danh sách để lưu trữ các đối tượng StudentEWBill
        List<StudentEWBill> billsList = new ArrayList<>();

        // Lặp qua danh sách Map và tạo đối tượng StudentEWBill từ mỗi Map
        if(billResults != null){
            billsList = maptoEWBill(billResults, billsList);
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("bills", billsList);
        model.addAttribute("currentSemester", currentSemester);
        model.addAttribute("semesterList",semesterList);
        model.addAttribute("roomId",roomId);
        return "adminEW";
    }

    @PostMapping("/ewadmin/{roomId}/detail")
    public String ewadmindata(@ModelAttribute("ew") EWDTO ewdto,@PathVariable("roomId") Long roomId){
        String semester = getSemester();
        LocalDate date = LocalDate.now().minusDays(7);
        int monthValue = date.getMonthValue();

        EWSemesterResponse ewrs = ewRepository.getRoomSemester(semester);

        int year = Integer.parseInt(ewrs.getSemesterName().replaceAll("[^0-9]", ""));

        ewdto.setMonth(monthValue);
        EWResponse ewResponse = ewRepository.getEW(year,ewdto.getMonth(),roomId);
        EWResponse ewPre = ewRepository.getEW(year, ewdto.getMonth() - 1, roomId);

        if(ewdto.getMonth() == 1){
            ewPre = ewRepository.getEW(year - 1, 12, roomId);
        }

        if (ewResponse == null && ewPre != null) {
            ewdto.setYear(year);
            ewdto.setRoomId(roomId);
            ewdto.setSemesterId(ewrs.getSemesterId());
            ewdto.setSemesterName(ewrs.getSemesterName());
            ewService.saveEW(ewdto,ewPre,roomId);
        }else if(ewPre == null && ewResponse == null) {
            ewdto.setYear(year);
            ewdto.setRoomId(roomId);
            ewdto.setSemesterId(ewrs.getSemesterId());
            ewdto.setSemesterName(ewrs.getSemesterName());
            ewService.saveEWPreNull(ewdto);
        } else{
            ewService.updateEW(ewResponse,ewdto,ewPre,roomId);
        }
        return "redirect:/ewadmin/{roomId}/detail";
    }

    @GetMapping("/studentEW/{studentId}")
    public String studentEW(Model model,@PathVariable("studentId") Long studentIdFE){
        List<Semester> semester = semesterRepository.findAll();
        String currentSemesterName = getSemester();
        Semester currentSemester = semesterRepository.findSemesterBySemesterName(currentSemesterName);

        List<Map<String, Object>> billResults = ewRepository.getBillStudent(studentIdFE, currentSemester.getId(),currentSemester.getId());

        // Danh sách để lưu trữ các đối tượng StudentEWBill
        List<StudentEWBill> bills = new ArrayList<>();

        // Lặp qua danh sách Map và tạo đối tượng StudentEWBill từ mỗi Map
        if(billResults != null){
            bills = maptoEWBill(billResults, bills);
        }

        model.addAttribute("bills", bills);
        model.addAttribute("currentSemester", currentSemester);
        model.addAttribute("semester",semester);
        model.addAttribute("studentId",studentIdFE);
        return "adminEW_student";
    }

    private List<StudentEWBill> maptoEWBill(List<Map<String, Object>> billResults, List<StudentEWBill> bills) {
        for (Map<String, Object> bill : billResults) {
            StudentEWBill studentEWBill = new StudentEWBillDTO(
                    (Long) bill.get("bedBookedId"),
                    (Long) bill.get("semesterId"),
                    (Long) bill.get("studentId"),
                    (Long) bill.get("roomId"),
                    (Integer) bill.get("month"),
                    (Integer) bill.get("year"),
                    (Long) bill.get("electricOld"),
                    (Long) bill.get("waterOld"),
                    (Long) bill.get("electricNew"),
                    (Long) bill.get("waterNew")
            );
            bills.add(studentEWBill);
        }
        return bills;
    }

    @GetMapping("/student/ew")
    public ResponseEntity<List<StudentEWBill>> studentBill(@RequestParam("studentId") Long studentIdFE, @RequestParam("semesterId") Long semesterIdFE) {
        System.out.println("studentId: " + studentIdFE + ", semesterId: " + semesterIdFE);

        // Lấy danh sách Map từ câu truy vấn SQL
        List<Map<String, Object>> billResults = ewRepository.getBillStudent(studentIdFE, semesterIdFE,semesterIdFE);

        // Danh sách để lưu trữ các đối tượng StudentEWBill
        List<StudentEWBill> bills = new ArrayList<>();
        bills = maptoEWBill(billResults, bills);

        // In danh sách bills để kiểm tra
        System.out.println("Bills: " + bills);

        // Trả về danh sách bills trong ResponseEntity
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/room/ew")
    public ResponseEntity<List<StudentEWBill>> RoomBill(@RequestParam("roomId") Long roomId, @RequestParam("semesterId") Long semesterIdFE) {
        System.out.println("studentId: " + roomId + ", semesterId: " + semesterIdFE);

        // Lấy danh sách Map từ câu truy vấn SQL
        List<Map<String, Object>> billResults = ewRepository.getBillRoom(roomId, semesterIdFE,semesterIdFE);

        // Danh sách để lưu trữ các đối tượng StudentEWBill
        List<StudentEWBill> bills = new ArrayList<>();
        bills = maptoEWBill(billResults, bills);

        // In danh sách bills để kiểm tra
        System.out.println("Bills: " + bills);

        // Trả về danh sách bills trong ResponseEntity
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
    @GetMapping("/room/student")
    public ResponseEntity<List<RoomStudentList>> RoomStudent(@RequestParam("roomId") Long roomId, @RequestParam("semesterId") Long semesterIdFE) {
        System.out.println("roomId: " + roomId + ", semesterId: " + semesterIdFE);

        Semester semester = semesterRepository.findById(semesterIdFE).get();
        List<RoomStudentList> studentList = studentRepository.getRoomStudentList(roomId,semester.getSemesterName());
        System.out.println("Bills: " + studentList);


        // Trả về danh sách bills trong ResponseEntity
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    public String getSemester(){
        LocalDate date = LocalDate.now().minusDays(7);
        int year = date.getYear();
        Month month = date.getMonth();
        String semester;
        if (month.getValue() >= 1 && month.getValue() <= 4) {
            semester = "Spring " + year;
        } else if (month.getValue() >= 5 && month.getValue() <= 8) {
            semester = "Summer " + year;
        } else {
            semester = "Fall " + year;
        }
        return semester;
    }
}
