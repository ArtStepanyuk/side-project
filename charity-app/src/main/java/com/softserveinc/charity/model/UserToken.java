package com.softserveinc.charity.model;

/**
 * Simple POJO for creating auth token.
 *
 */
public class UserToken {
    private String name;
    private String password;
    private long expires;

    public UserToken(){}

    public UserToken(String name, String password, long expires) {
        this.name = name;
        this.password = password;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

}
