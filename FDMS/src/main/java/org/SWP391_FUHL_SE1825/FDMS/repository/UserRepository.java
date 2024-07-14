package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNameAndPassword(String username, String password);
    UserEntity findByUserName(String username);
    Boolean existsUserByUserName(String username);

    Optional<UserEntity> getUserEntityByFullName(String fullname);
    @Query("SELECT u from UserEntity u WHERE u.fullName LIKE %?1% ")
    Page<UserEntity> searchUser(String query, Pageable pageable);
}
