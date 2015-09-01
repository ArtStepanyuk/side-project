package com.softservinc.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "findUserByEmail",
                query = "from User u where u.email = :email"
        )
})
public class User implements UserDetails {

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String username, Date expires) {
        this.username = username;
        this.expires = expires.getTime();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserAuthority> authorities;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Transient
    private String username;

    @Transient
    private long expires;

    @Transient
    private String token;

    public Integer getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setUsername(String username)
    {
        setEmail(username);
        this.username = username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
