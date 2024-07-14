package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IUserService {
    UserEntity getOneUser(Long id);
    public UserEntity updateEmployee(Long id, UserEntity userEntity);
    public List<UserEntity> getAllUser(Sort id);
    UserEntity getUser(String username);
    UserEntity login(String username, String password) ;
    void updateUser(UserEntity user);
    public Page<UserEntity> searchUser(String query,Pageable pageable)  ;
    Page<ManagerSecondaryInformation> list(Pageable pageable);
    public void maptoUser(UserEntity mapUser, UserEntity user);
}
