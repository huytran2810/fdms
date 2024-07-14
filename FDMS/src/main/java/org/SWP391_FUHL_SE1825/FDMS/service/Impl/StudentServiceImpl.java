package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.dto.StudentDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.repository.StudentRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<StudentSecondaryInformation> getAllStudentPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<StudentDTO> searchStudent(String query,Pageable pageable) {
        return studentRepository.searchStudent(query,pageable).map(this::maptoDTO);
    }
    @Override
    public List<Payment> searchStudentpayments(Long query) {
        List<Payment> student = studentRepository.searchStudentpayments(query);
        return student;
    }
    @Override
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(this::maptoDTO);
    }

    @Override
    public StudentDTO getOneStudents(Long studentId){
        return studentRepository.findById(studentId).map(this::maptoDTO).get();
    }

    @Override
    public StudentDTO maptoDTO(StudentSecondaryInformation student){
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setUserId(student.getUserInfor().getId());
        dto.setAvatarImage(student.getUserInfor().getAvatarImage());
        dto.setRollNumber(student.getRollNumber());
        dto.setEmail(student.getUserInfor().getEmail());
        dto.setDob(student.getUserInfor().getDateOfBirth());
        dto.setFullName(student.getUserInfor().getFullName());
        dto.setGender(student.getUserInfor().getGender());
        dto.setPhone(student.getUserInfor().getPhone());
        dto.setIsInDorm(student.getIsInDorm());
        dto.setAddress(student.getUserInfor().getAddress());
        dto.setStatus(student.getUserInfor().getStatus());
        return dto;
    }
}
