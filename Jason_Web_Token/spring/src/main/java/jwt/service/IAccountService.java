package jwt.service;

import jwt.model.Account;

public interface IAccountService {
    Account findByUserName(String userName);
}
