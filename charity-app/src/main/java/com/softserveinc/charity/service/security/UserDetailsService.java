package com.softserveinc.charity.service.security;

import com.softserveinc.charity.model.User;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService
		implements org.springframework.security.core.userdetails.UserDetailsService {

//	@Autowired
//	private UserDao userDao;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = null;//userDao.getByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
}
