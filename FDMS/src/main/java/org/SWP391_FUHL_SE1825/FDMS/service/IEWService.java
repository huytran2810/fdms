package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.EWDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.EWResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.ElectricWaterUsed;

public interface IEWService {
    void saveEW(EWDTO ewdto,EWResponse ewPre,Long roomId);

    ElectricWaterUsed maptoEW(EWDTO ewdto,EWResponse ewPre,Long roomId);

    void updateEW(EWResponse ewResponse, EWDTO ewdto,EWResponse ewPre,Long roomId);

    void saveEWPreNull(EWDTO ewdto);
}
