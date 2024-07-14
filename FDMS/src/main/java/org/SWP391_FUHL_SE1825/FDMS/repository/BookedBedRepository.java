package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BedBookedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.BedBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookedBedRepository extends JpaRepository<BedBooked, Integer> {
    @Query(value = "SELECT bo.bed_booked_id as bookedId, bo.bed_id as bedId, b.bed_name AS bedName, s.semester_name AS semesterName,\n" +
            "r.room_name AS roomName, s.start_date as checkIn, r.price AS price, bo.status AS status,  s.end_date as checkOut, bo.created_at as createdDate\n" +
            " FROM bed_booked bo JOIN bed b ON bo.bed_id = b.bed_id \n" +
            "JOIN semester s ON bo.semester_id = s.semester_id JOIN room r ON r.room_id = b.room_id WHERE bo.student_id = ?", nativeQuery = true)
    List<BedBookedResponse> findByStudentId(Long studentId);

    @Query(value = "SELECT * FROM bed_booked where student_id = ? AND status = 'pending' OR status = 'accept'", nativeQuery = true)
    BedBooked checkExist(Long id);
}
