package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestApplication, Long> {
    @Query(value = "INSERT INTO `request_application`\n" +
            "(\n" +
            "`created_at`,\n" +
            "`request_content`,\n" +
            "`status`,\n" +
            "`text_response`,\n" +
            "`updated_at`,\n" +
            "`take_by_manager_id`,\n" +
            "`request_application_type_id`,\n" +
            "`bed_booked_id`)\n" +
            "VALUES\n" +
            "(\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?);", nativeQuery = true)
    void requestBooking(String content, String status, String managerId, int typeId, int bedBookedId);
    @Query(value = "SELECT ra.request_application_id as ID, rat.request_application_type_name as Type, u.full_name as Name, b.bed_name as bedName, " +
            "ra.request_content as Content , ra.status , ra.text_response as Respone, ra.created_at as CreateAt " +
            "FROM request_application ra " +
            "INNER JOIN request_application_type rat ON ra.request_application_type_id = rat.request_application_type_id " +
            "LEFT JOIN bed_booked book ON book.bed_booked_id = ra.bed_booked_id " +
            "LEFT JOIN student_secondary_information stu ON stu.student_id = book.student_id " +
            "INNER JOIN user u ON u.id = stu.user_id " +
            "LEFT JOIN bed b ON book.bed_id = b.bed_id " +
            "INNER JOIN semester s ON s.semester_id = book.semester_id",
            nativeQuery = true)
    Page<RequestRespone> findRequestApplications(Pageable page);
}
