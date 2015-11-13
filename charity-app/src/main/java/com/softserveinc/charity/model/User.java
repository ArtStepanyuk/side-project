package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserveinc.charity.model.offer.Offer;
import org.springframework.data.annotation.Id;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.userdetails.UserDetails;
import com.softserveinc.charity.model.need.Need;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
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
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<UserRole> roles = new HashSet<>();


   @OneToMany(mappedBy = "userCreated", fetch = FetchType.EAGER)
   @JsonIgnoreProperties("userCreated")
    private Set<Need> needs;

    @OneToMany(mappedBy = "userCreated", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("userCreated")
    private Set<Offer> offers;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @RestResource(exported = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Transient
    private String username;

    @Transient
    private long expires;

    @Transient
    private String token;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    private Set<OfferResponse> offerResponses;


    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    private Set<NeedResponse> needResponses;


    public Integer getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public Set<UserAuthority> getAuthorities() {
        return getRoles().stream().
                map(userRole -> userRole.asAuthorityFor(this))
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonProperty
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

    @JsonProperty
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

    @JsonIgnore
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Address getAddress() {
        return address;
    }
    @JsonProperty
    public void setAddress(Address address) {
        this.address = address;
    }
    @JsonProperty
    public void setOfferResponses(Set<OfferResponse> offerResponses) {
        this.offerResponses = offerResponses;
    }
    @JsonProperty
    public void setNeedResponses(Set<NeedResponse> needResponses) {
        this.needResponses = needResponses;
    }

    public Set<NeedResponse> getNeedResponses() {
        return needResponses;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public void setNeeds(Set<Need> needs) {
        this.needs = needs;
    }

    public Set<OfferResponse> getOfferResponses() {
        return offerResponses;
   }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
