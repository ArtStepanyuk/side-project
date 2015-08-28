package com.softservinc.charity.web.security;


public interface UserRepository
		//extends JpaRepository<User, Long>
{

	User findByUsername(String username);
}
