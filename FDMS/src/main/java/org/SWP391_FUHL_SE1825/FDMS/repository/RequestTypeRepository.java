package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestTypeRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface RequestTypeRepository extends JpaRepository<RequestApplicationType,Long> {
    @Query("select retype.id As id ,retype.requestApplicationTypeName As name"+
            " from RequestApplicationType retype where retype.permistion ='ROLE_MANAGER' or retype.permistion= 'ALL'")
    List<RequestTypeRespone> findByManager();
    @Query("select retype.id As id ,retype.requestApplicationTypeName As name"+
            " from RequestApplicationType retype where retype.permistion ='ROLE_STUDENT' or retype.permistion= 'ALL'")
    List<RequestTypeRespone> findByStudent();
    RequestApplicationType findById(long id);
}
