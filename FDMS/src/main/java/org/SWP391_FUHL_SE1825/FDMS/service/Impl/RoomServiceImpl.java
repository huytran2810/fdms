package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.SWP391_FUHL_SE1825.FDMS.entity.Building;
import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.SWP391_FUHL_SE1825.FDMS.repository.BedCommandRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.BuildingRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.RoomRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements IRoomService {
    private final RoomRepository roomRepository;
    private final BedCommandRepository bedRepository;
    private final BuildingRepository buildingRepository;
    @Override
    public Page<Room> list(int index, String name, Integer buildingId) {
        Pageable paging = PageRequest.of(index, 10);
        return roomRepository.findAllByRoomNameAndBuildingId(name, buildingId == null ? -1 : buildingId, paging);
    }

    @Override
    public void add(long buildingId, int floor, String roomType, int numRoom) {
        Building building = buildingRepository.findBuildingById(buildingId);
        char buildingCode = building.getBuildingname().charAt(building.getBuildingname().length() - 1);

        int numPerson = Integer.parseInt(roomType.replaceAll("\\D+", ""));
        List<Bed> beds = new ArrayList<>();

        float price = 0;
        switch (roomType) {
            case "phòng 3 người":
                price = 4600000;
                break;
            case "phòng 4 người":
                price = 4200000;
                break;
            case "phòng 6 người":
                price = 3800000;
                break;
        }

        for (int i = 1; i <= numRoom; i++) {
            String roomName = String.format("%s%d%02d", buildingCode, floor, i);

            Room room = new Room();
            room.setRoomName(roomName);
            room.setBuildingId(buildingId);
            room.setFloor(floor);
            room.setStatus("available");
            room.setRoom_type_desciption(roomType);
            room.setPrice(price);

            Room savedRoom = roomRepository.save(room);

            for (int j = 1; j <= numPerson; j++) {
                String bedName = roomName + " - Bed 0" + j;

                Bed bed = new Bed();
                bed.setBedName(bedName);
                bed.setRoomId(room.getId());
                bed.setStatus("vacant");
                beds.add(bed);
            }
        }

        bedRepository.saveAll(beds);
    }


}
