package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.dto.StudentDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BedBookedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.BookedBedRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.StudentRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IStudentService;
import org.SWP391_FUHL_SE1825.FDMS.service.Impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookedBedRepository bookedBedRepository;

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @GetMapping("/admin/student-list")
    public String getAllStudent(@RequestParam(value = "page", defaultValue = "0") int page,Model model) {
        Page<StudentDTO> studentPage = studentServiceImpl.getAllStudents(PageRequest.of(page,10));
        model.addAttribute("student", studentPage.getContent() );
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        return "students";
    }

    @GetMapping("/admin/student-list/search")
    public String searchStudent(@RequestParam(value = "query") String query,@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        query = query.trim();
        Page<StudentDTO> studentPage = studentServiceImpl.searchStudent(query, PageRequest.of(page,10));
        model.addAttribute("student", studentPage );
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        return "students";
    }
    @PostMapping("/admin/student/{studentId}/activate")
    public ResponseEntity<String> activeStu(@PathVariable("studentId") Long studentId) {
        UserEntity stu = userRepository.findById(studentId).get();
        if (!stu.getStatus().equals("active")){
            stu.setStatus("active");
            userRepository.save(stu);
            return ResponseEntity.ok("Active Student successfully");

        }else {
            logger.error("Failed to active Student");
            return ResponseEntity.badRequest().body("Student already activated");
        }

    }
    @PostMapping("/admin/student/{studentId}/deactivate")
    public ResponseEntity<String> deactivateStu(@PathVariable("studentId") Long studentId) {
        UserEntity stu = userRepository.findById(studentId).get();
        if (stu.getStatus().equals("active")){
            stu.setStatus("unactive");
            userRepository.save(stu);
            return ResponseEntity.ok("Deactivate Student successfully");

        }else {
            logger.error("Failed to deactivate Student");
            return ResponseEntity.badRequest().body("Student already deactivated");
        }

    }

    @GetMapping("/admin/student/{studentId}/detail")
    public String detailStudent(@PathVariable("studentId") Long studentID, Model model) {
        StudentDTO stu = iStudentService.getOneStudents(studentID);
        List<BedBookedResponse> bedbooked = bookedBedRepository.findByStudentId(studentID) ;
        model.addAttribute("student", stu );
        model.addAttribute("bedbooked", bedbooked );
        return "student-detail";
    }
}
