package com.example.authorizationserver.app.service.user;

import com.example.authorizationserver.app.entity.AppUser;
import com.example.authorizationserver.app.repository.IAppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IAppUserRepository iAppUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = this.iAppUserRepository.findByEmailAndIsEnable(email, true);
        if (appUser == null) {
            throw new UsernameNotFoundException("Not found user : " + email);
        }
        return new User(appUser.getEmail(),
                appUser.getPassword(),
                appUser.isEnable(), true, true, true,
                List.of(new SimpleGrantedAuthority(appUser.getRole())));
    }
}
