package com.softservinc.charity.entity.security;

public enum UserRole {
	USER, ADMIN;

	public UserAuthority asAuthorityFor(final User user) {
		final UserAuthority authority = new UserAuthority();
		authority.setAuthority("ROLE_" + toString());
		authority.setUser(user);
		return authority;
	}

	public static UserRole valueOf(final UserAuthority authority) {
		if ("ROLE_USER".equals(authority.getAuthority()))
			return USER;
		if ("ROLE_ADMIN".equals(authority.getAuthority()))
			return ADMIN;
		throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}
}

