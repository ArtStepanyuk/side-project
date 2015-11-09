package com.softserveinc.charity.service;

import com.softserveinc.charity.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getCurrentUser();

}
