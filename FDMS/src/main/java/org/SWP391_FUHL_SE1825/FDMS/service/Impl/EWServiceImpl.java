package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.dto.EWDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.ElectricWaterUsed;
import org.SWP391_FUHL_SE1825.FDMS.repository.EWRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.RoomRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.SemesterRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IEWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EWServiceImpl implements IEWService {

    private final RoomRepository roomRepository;
    private final SemesterRepository semesterRepository;
    @Autowired
    private EWRepository ewRepository;
    public EWServiceImpl(RoomRepository roomRepository, SemesterRepository semesterRepository) {
        this.roomRepository = roomRepository;
        this.semesterRepository = semesterRepository;
    }

    @Override
    public void saveEW(EWDTO ewdto, EWResponse ewPre,Long roomId) {
        ElectricWaterUsed ew = maptoEW(ewdto,ewPre,roomId);
        ewRepository.save(ew);
    }
     @Override
    public ElectricWaterUsed maptoEW(EWDTO ewdto,EWResponse ewPre,Long roomId){
        ElectricWaterUsed ew = new ElectricWaterUsed();
        ew.setRoomId(roomId);
        ew.setSemesterId(ewdto.getSemesterId());
        ew.setElectricOld(ewPre.getElectricNew());
        ew.setWaterOld(ewPre.getWaterNew());
        ew.setElectricNew(ewdto.getElectric());
        ew.setWaterNew(ewdto.getWater());
        ew.setMonthIndex(ewdto.getMonth());
        ew.setYearIndex(ewdto.getYear());
        return ew;
     }

    @Override
    public void updateEW(EWResponse ewResponse, EWDTO ewdto,EWResponse ewPre,Long roomId ){
        ElectricWaterUsed ew = new ElectricWaterUsed();
        ew.setId(ewResponse.getId());
        ew.setRoomId(roomId);
        ew.setSemesterId(ewResponse.getSemesterId());
        ew.setElectricOld(ewPre.getElectricNew());
        ew.setWaterOld(ewPre.getWaterNew());
        ew.setElectricNew(ewdto.getElectric());
        ew.setWaterNew(ewdto.getWater());
        ew.setMonthIndex(ewResponse.getMonth());
        ew.setYearIndex(ewResponse.getYear());
        ewRepository.save(ew);
    }

    @Override
    public void saveEWPreNull(EWDTO ewdto) {
        ElectricWaterUsed ew = new ElectricWaterUsed();
        ew.setRoomId(ewdto.getRoomId());
        ew.setSemesterId(ewdto.getSemesterId());
        ew.setElectricOld(null);
        ew.setWaterOld(null);
        ew.setElectricNew(ewdto.getElectric());
        ew.setWaterNew(ewdto.getWater());
        ew.setMonthIndex(ewdto.getMonth());
        ew.setYearIndex(ewdto.getYear());
        ewRepository.save(ew);
    }
}
