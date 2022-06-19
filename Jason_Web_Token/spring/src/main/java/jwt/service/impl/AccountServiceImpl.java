package jwt.service.impl;

import jwt.model.Account;
import jwt.repository.IAccountRepository;
import jwt.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    IAccountRepository iAccountRepository;

    @Override
    public Account findByUserName(String userName) {
        return this.iAccountRepository.findByUserName(userName);
    }
}
