package com.softservinc.charity.dao;


import com.softservinc.charity.entity.security.User;

public interface UserRepository
		//extends JpaRepository<User, Long>
{

	User findByUsername(String username);
}
