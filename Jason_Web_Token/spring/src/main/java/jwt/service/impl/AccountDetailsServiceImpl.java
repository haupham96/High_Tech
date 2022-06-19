package jwt.service.impl;

import jwt.model.Account;
import jwt.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IAccountRepository iAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.iAccountRepository.findByUserName(username);
        if (account == null) {
            throw new UsernameNotFoundException("userName: " + username + " Not found !");
        }
        return AccountDetailsImpl.build(account);
    }
}
