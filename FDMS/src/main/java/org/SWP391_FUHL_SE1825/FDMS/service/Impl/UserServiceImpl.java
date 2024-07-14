package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.ManagerRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public Page<UserEntity> searchUser(String query,Pageable pageable) {
        Page<UserEntity> user = userRepository.searchUser(query,pageable);
        return user;
    }

    @Override
    public Page<ManagerSecondaryInformation> list(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @Override
    public UserEntity getOneUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity updateEmployee(Long id, UserEntity userEntity) {
        UserEntity userEntity1 = userRepository.findById(id).get();
        if (userEntity1 != null) {
            userEntity1.setUserName(userEntity.getUserName());
            userEntity1.setAddress(userEntity.getAddress());
            userEntity1.setEmail(userEntity.getEmail());
            userEntity1.setPhone(userEntity.getPhone());
            userEntity1.setPassword(userEntity.getPassword());
            userEntity1.setDateOfBirth(userEntity.getDateOfBirth());
            userEntity1.setGender(userEntity.getGender());
            userEntity1.setFullName(userEntity.getFullName());
            userEntity1.setStatus(userEntity.getStatus());
            userEntity1.setId(userEntity.getId());
            return userRepository.save(userEntity1);
        }
        return null;

    }

    @Override
    public List<UserEntity> getAllUser(Sort id) {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUserName(username);
    }


    @Override
    public UserEntity login(String username, String password) {
        return null;
    }



    @Override
    public void updateUser(UserEntity user) {
        UserEntity user1 = UserEntity.builder()
                .id(user.getId())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .password(user.getPassword())
                .phone(user.getPhone())
                .status(user.getStatus())
                .updatedAt(user.getUpdatedAt())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
        userRepository.save(user1);
    }

    @Override
    public void maptoUser(UserEntity user1, UserEntity user){
        user1.setId(user.getId());
        user1.setGender(user.getGender());
        user1.setStatus(user.getStatus());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setPassword(user.getPassword());
        user1.setCreatedAt(user.getCreatedAt());
        user1.setUpdatedAt(user.getUpdatedAt());
        user1.setAdminSecondaryInformations(user.getAdminSecondaryInformations());
        user1.setStudentSecondaryInformations(user.getStudentSecondaryInformations());
        user1.setRole(user.getRole());
        user1.setUserName(user.getUserName());
        userRepository.save(user1);
    }

}
