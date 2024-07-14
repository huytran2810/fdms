package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWSemesterResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RoomDetailResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    @Query(value = "Select * from room where (:name = '' OR room_name = :name) AND (:buildingId  = -1 OR building_id = :buildingId)", nativeQuery = true)
    Page<Room> findAllByRoomNameAndBuildingId(String name, int buildingId, Pageable pageable);
    List<Room> findAll();
    @Query(value = "SELECT * FROM room where room_id = ?;", nativeQuery = true)
    Room findByRoomId(Long roomID);
    @Modifying
    @Query(value = "DELETE FROM room WHERE room_id = :roomId", nativeQuery = true)
    void deleteById(@Param("roomId") int roomId);

    Room save(Room room);

    @Query(value = "SELECT room_id FROM bed WHERE bed_id = ?", nativeQuery = true)
    Long findRoomIdByBedId(Long bedId);

    @Query(value = "SELECT b.bed_id AS bedId, u.full_name as fullName, s.roll_number AS rollNumber,\n" +
            "b.bed_name as bedName\n" +
            "FROM bed b\n" +
            "LEFT JOIN bed_booked bb ON b.bed_id = bb.bed_id\n" +
            "LEFT JOIN student_secondary_information s ON s.student_id = bb.student_id\n" +
            "LEFT JOIN user u ON u.id = s.user_id\n" +
            "WHERE b.room_id = ?", nativeQuery = true)
    List<RoomDetailResponse> getRoomDetails(Integer roomId);

}
