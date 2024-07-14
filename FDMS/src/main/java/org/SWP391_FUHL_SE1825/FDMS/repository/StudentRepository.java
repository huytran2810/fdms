package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.StudentAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RoomDetailResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RoomStudentList;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentSecondaryInformation, Long> {
    Optional<StudentSecondaryInformation> getStudentSecondaryInformationByRollNumber(String rollNumber);
    @Query("SELECT s from StudentSecondaryInformation s WHERE s.rollNumber LIKE %?1% ")
    List<StudentSecondaryInformation> searchStudent( String query);

    @Query(value = "SELECT s.* FROM student_secondary_information s \n" +
            "JOIN user u ON s.user_id = u.id WHERE u.user_name = ?", nativeQuery = true)
    StudentSecondaryInformation findByUsername(String username);
    @Query("SELECT s from StudentSecondaryInformation s WHERE s.rollNumber LIKE concat('%',:query,'%') or s.userInfor.fullName Like %:query%")
    Page<StudentSecondaryInformation> searchStudent(String query, Pageable pageable);

    @Query("SELECT s from Payment s WHERE s.student.id = :studentId AND s.requestApplication.requestApplicationType.id = 1")
    List<Payment> searchStudentpayments(Long studentId);
    @Query("SELECT s.userInfor.fullName FROM StudentSecondaryInformation s WHERE s.userInfor.fullName LIKE %:query%")
    List<String> searchStudentName(String query, Pageable pageable);

    @Query("SELECT new org.SWP391_FUHL_SE1825.FDMS.dto.StudentAJDTO(s.id, CONCAT(s.rollNumber, ' - ', s.userInfor.fullName)) FROM StudentSecondaryInformation s WHERE s.rollNumber LIKE %:query% or s.userInfor.fullName Like %:query%")
    List<StudentAJDTO> searchStudentAJ(String query, Pageable pageable);

    @Query(value = "SELECT s.student_id as id,CONCAT(s.roll_number, ' - ', u.full_name, ' - ', TRIM(SUBSTRING_INDEX(u.address, ',', -1))) AS name\n" +
            "FROM bed_booked bb left JOIN bed b ON bb.bed_id = b.bed_id\n" +
            "left JOIN student_secondary_information s ON bb.student_id = s.student_id\n" +
            "left JOIN `user` u ON s.user_id = u.id\n" +
            "left join semester smt on bb.semester_id = smt.semester_id \n" +
            "WHERE b.room_id = ? AND smt.semester_name = ?", nativeQuery = true)
    List<RoomStudentList> getRoomStudentList(Long roomId, String semesterName);
}
