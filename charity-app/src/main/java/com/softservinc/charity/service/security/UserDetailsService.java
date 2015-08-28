package com.softservinc.charity.service.security;

import com.softservinc.charity.entity.security.User;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService
		implements org.springframework.security.core.userdetails.UserDetailsService {

//	@Autowired
//	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
//		final User user = userRepo.findByUsername(username);
		final User user = new User("asd");
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
}
