package com.infoshare.myfitwebapp.service;

import com.infoshare.myfitwebapp.entity.UserLogin;
import com.infoshare.myfitwebapp.model.UserPrincipal;
import com.infoshare.myfitwebapp.repository.UserLoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{


    private final UserLoginRepository userRepository;

    public MyUserDetailsService(UserLoginRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        UserLogin userLogin = userRepository.findByUsername(username);
        if(userLogin == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userLogin);
    }
}
