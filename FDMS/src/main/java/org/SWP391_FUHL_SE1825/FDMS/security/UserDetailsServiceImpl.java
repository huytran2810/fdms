package org.SWP391_FUHL_SE1825.FDMS.security;

import jakarta.transaction.Transactional;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.RoleRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        return UserDetailsImpl.build(user);
    }

}
