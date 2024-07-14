package org.SWP391_FUHL_SE1825.FDMS.repository;

import jakarta.transaction.Transactional;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.AvailableBedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BedRepository extends PagingAndSortingRepository<Bed, Long> {
    @Query(value = "SELECT bu.building_id as buildingId, bu.building_name as buildingName, b.room_id as roomId, m.room_name as roomName,\n" +
            "COUNT(b.bed_id) AS totalBed,\n" +
            "SUM(CASE WHEN b.status = 'vacant' THEN 1 ELSE 0 END) as AvailableBed, \n" +
            "SUM(CASE WHEN b.status = 'booked' THEN 1 ELSE 0 END) as UsedBed\n" +
            "FROM Bed b JOIN room m ON b.room_id = m.room_id\n" +
            "JOIN building bu ON m.building_id = bu.building_id\n" +
            "WHERE (:status = '-1' OR b.status = :status) AND (:roomId = 0 OR b.room_id = :roomId) \n" +
            "GROUP BY  bu.building_id, bu.building_name, b.room_id, m.room_name",
            countQuery = "SELECT COUNT(*) FROM (SELECT bu.building_id as buildingId, bu.building_name as buildingName, b.room_id as roomId, m.room_name as roomName,\n" +
                    "COUNT(b.bed_id) AS totalBed,\n" +
                    "SUM(CASE WHEN b.status = 'vacant' THEN 1 ELSE 0 END) as AvailableBed, \n" +
                    "SUM(CASE WHEN b.status = 'booked' THEN 1 ELSE 0 END) as UsedBed\n" +
                    "FROM Bed b JOIN room m ON b.room_id = m.room_id\n" +
                    "JOIN building bu ON m.building_id = bu.building_id\n" +
                    "WHERE (:status = '-1' OR b.status = :status) AND (:roomId = 0 OR b.room_id = :roomId) \n" +
                    "GROUP BY  bu.building_id, bu.building_name,  b.room_id, m.room_name) AS subquery",
            nativeQuery = true)
    Page<AvailableBedResponse> findAvailableBeds(Pageable pageable,  @Param("status") String status, @Param("roomId") Integer roomId );

    @Query(value = "SELECT * FROM bed " +
            "WHERE (:status = '-1' OR status = :status) AND (:roomId = 0 OR room_id = :roomId)", nativeQuery = true)
    Page<Bed> findAllByStatus(Pageable pageable, @Param("status") String status, @Param("roomId") Integer roomId);

    @Query(value = "SELECT * FROM bed WHERE status = 'vacant' AND room_id = ?;", nativeQuery = true)
    List<Bed> findBedBooking(Long  roomId);

    @Query(value = "SELECT * FROM bed WHERE bed_id = ?;", nativeQuery = true)
    Bed findBedById(Long  bedId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bed SET status = 'booked' WHERE bed_id = :bedId", nativeQuery = true)
    void updateStatus(@Param("bedId") Long bedId);
}


