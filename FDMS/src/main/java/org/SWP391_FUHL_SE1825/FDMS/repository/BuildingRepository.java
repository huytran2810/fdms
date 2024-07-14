package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.BuildingListResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByBuildingname(String buildingName);
    Building findBuildingById(Long id);
    Building findBuildingByBuildingname(String buildingName);
    @Query(value = "SELECT f.floor\n" +
            "FROM fdms_final.building b\n" +
            "CROSS JOIN (\n" +
            "    SELECT DISTINCT floor\n" +
            "    FROM fdms_final.room\n" +
            ") AS f\n" +
            "LEFT JOIN (\n" +
            "    SELECT DISTINCT building_id, floor\n" +
            "    FROM fdms_final.room\n" +
            ") AS r ON b.building_id = r.building_id AND f.floor = r.floor\n" +
            "WHERE r.building_id IS NULL AND b.building_id = ?\n" +
            "ORDER BY b.building_id, f.floor;\n", nativeQuery = true)
    List<Integer> findAllBuilding(Long buildingId);

    @Query(value = "SELECT b.building_id as buildingId, b.building_name as buildingName, b.number_floor as numberOfFloors\n" +
            "FROM fdms_final.building b\n" +
            "LEFT JOIN (\n" +
            "    SELECT building_id, COUNT(DISTINCT floor) AS floors_count\n" +
            "    FROM fdms_final.room\n" +
            "    GROUP BY building_id\n" +
            ") AS r ON b.building_id = r.building_id\n" +
            "WHERE r.floors_count < b.number_floor OR r.floors_count IS NULL;", nativeQuery = true)
    List<BuildingListResponse> findAllBuildings();
    @Query(value = "SELECT b.building_id AS buildingId, b.building_name AS buildingName, b.number_floor AS numberOfFloors, b.status AS status, " +
            "COUNT(r.room_id) AS roomCount, " +
            "COUNT(CASE WHEN r.status = 'available' THEN r.room_id END) AS availableRoomCount " +
            "FROM building b " +
            "LEFT JOIN room r ON r.building_id = b.building_id " +
            "GROUP BY b.building_id, b.building_name, b.number_floor, b.status", nativeQuery = true)
    Page<BuildingListResponse> findBuildingRoomCounts(Pageable page);
    @Query(value = "SELECT r.room_name FROM room r " +
            "INNER JOIN bed b ON r.room_id = b.room_id " +
            "INNER JOIN bed_booked book ON b.bed_id = book.bed_id " +
            "INNER JOIN building ON building.building_id = r.building_id " +
            "WHERE book.status = 'accepted' " +
            "AND  book.semester_id = :semester_id " +
            "AND building.building_id = :building_id",
            nativeQuery = true)
    List<String> getRoomByStudent(@Param("semester_id") Long semesterId, @Param("building_id") Long buildingId);


}
