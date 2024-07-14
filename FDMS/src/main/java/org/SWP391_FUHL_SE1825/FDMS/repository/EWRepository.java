package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWSemesterResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.StudentEWBill;
import org.SWP391_FUHL_SE1825.FDMS.entity.ElectricWaterUsed;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EWRepository extends JpaRepository<ElectricWaterUsed, Long> {
    @Query(value = "SELECT s.semester_id as semesterId, s.semester_name as semesterName\n" +
            "from semester s \n" +
            "WHERE s.semester_name = ? LIMIT 1", nativeQuery = true)
    EWSemesterResponse getRoomSemester(String semester);

    @Query(value = "select electric_water_used_id as id, room_id as roomId,\n" +
            "semester_id as semesterId, electric_old as electricOld, \n" +
            "water_old as waterOld, electric_new as electricNew, \n" +
            "water_new as waterNew, month_index as month, year_index as year\n" +
            " from electric_water_used where year_index = ?  and month_index = ? and room_id = ? LIMIT 1", nativeQuery = true)
    EWResponse getEW(Integer yearIndex, Integer monthIndex, Long roomId);

    @Query(value = "select bb.bed_booked_id as bedBookedId, bb.semester_id as semesterId,bb.student_id as studentId, b.room_id as roomId,e.month_index as month,\n" +
            " e.year_index as year, e.electric_old as electricOld,e.water_old as waterOld,e.electric_new as electricNew,e.water_new as waterNew  from bed_booked bb \n" +
            "join bed b on bb.bed_id = b.bed_id\n" +
            "join electric_water_used e on e.room_id = b.room_id\n" +
            "join semester s on s.semester_id = e.semester_id\n" +
            "where bb.student_id = ? and s.semester_name = ? and bb.semester_id = ?", nativeQuery = true)
    List<StudentEWBill> getBills(Long studentId, String semesterName, Long semesterId);

    @Query(value = "select bb.bed_booked_id as bedBookedId, bb.semester_id as semesterId,bb.student_id as studentId, b.room_id as roomId,e.month_index as month,\n" +
            " e.year_index as year, e.electric_old as electricOld,e.water_old as waterOld,e.electric_new as electricNew,e.water_new as waterNew  from bed_booked bb \n" +
            "join bed b on bb.bed_id = b.bed_id\n" +
            "join electric_water_used e on e.room_id = b.room_id\n" +
            "join semester s on s.semester_id = e.semester_id\n" +
            "where b.room_id = ? and s.semester_name = ? and bb.semester_id = ?", nativeQuery = true)
    List<StudentEWBill> getRoomUsedList(Long roomId, String semesterName, Long semesterId);

    @Query(value = "select bb.bed_booked_id as bedBookedId, bb.semester_id as semesterId, bb.student_id as studentId, b.room_id as roomId, e.month_index as month," +
            " e.year_index as year, e.electric_old as electricOld, e.water_old as waterOld, e.electric_new as electricNew, e.water_new as waterNew from bed_booked bb " +
            "join bed b on bb.bed_id = b.bed_id join electric_water_used e on e.room_id = b.room_id where bb.student_id = ?1 and bb.semester_id = ?2 and e.semester_id = ?3 ", nativeQuery = true)
    List<Map<String, Object>> getBillStudent(Long studentId, Long semesterId, Long semesterId2);

    @Query(value = "select bb.bed_booked_id as bedBookedId, bb.semester_id as semesterId, bb.student_id as studentId, b.room_id as roomId, e.month_index as month," +
            " e.year_index as year, e.electric_old as electricOld, e.water_old as waterOld, e.electric_new as electricNew, e.water_new as waterNew from bed_booked bb " +
            "join bed b on bb.bed_id = b.bed_id join electric_water_used e on e.room_id = b.room_id where e.room_id =?1 and bb.semester_id = ?2 and e.semester_id = ?3 ", nativeQuery = true)
    List<Map<String, Object>> getBillRoom(Long roomId, Long semesterId, Long semesterId2);
}


