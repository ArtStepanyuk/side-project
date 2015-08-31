package com.softservinc.charity.model;

import com.softservinc.charity.model.UserAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private String role;

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserAuthority asAuthorityFor(final User user) {
        final UserAuthority authority = new UserAuthority();
        authority.setAuthority("ROLE_" + toString());
        authority.setUser(user);
        return authority;
    }
}
