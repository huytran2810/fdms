package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.entity.Room;
import org.springframework.data.domain.Page;

public interface IRoomService {
    Page<Room> list(int index, String name, Integer buildingId);
    void add(long buildingId, int floor, String roomType, int numRoom);

}
