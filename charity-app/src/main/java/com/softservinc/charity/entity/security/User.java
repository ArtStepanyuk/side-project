package com.softservinc.charity.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, Date expires) {
		this.username = username;
		this.expires = expires.getTime();
	}

	private Long id;

	private String username;

	private String password;

	@Transient
	private long expires;

	private boolean accountExpired;

	private boolean accountLocked;

	private boolean credentialsExpired;

	private boolean accountEnabled;

	@Transient
	private String newPassword;

	private Set<UserAuthority> authorities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getUsername();
	}
}
