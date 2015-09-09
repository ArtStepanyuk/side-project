package com.softservinc.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Workaround for serializing user password to json in tests.
 */
public class TestUser extends User {

    @Override
    @JsonIgnore(value = false)
    public String getPassword() {
        return super.getPassword();
    }
}
