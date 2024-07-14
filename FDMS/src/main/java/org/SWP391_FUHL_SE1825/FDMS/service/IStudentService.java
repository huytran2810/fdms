package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.StudentDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<StudentSecondaryInformation> getAllStudentPage(Pageable pageable);
    Page<StudentDTO> searchStudent(String query,Pageable pageable);
    public List<Payment> searchStudentpayments(Long query);
    public StudentDTO getOneStudents(Long studentId);
    public Page<StudentDTO> getAllStudents(Pageable pageable);

    StudentDTO maptoDTO(StudentSecondaryInformation student);
}
