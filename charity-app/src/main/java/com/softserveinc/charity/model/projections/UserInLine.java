package com.softserveinc.charity.model.projections;

import com.softserveinc.charity.model.Address;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "InLine", types = { User.class })
public interface UserInLine {

    Address getAddress();

    @Value("#{target.address.city}")
    City getCity();

    Set<UserRole> getRoles();

    long getExpires();

    String getUsername();

    String getEmail();

    String getToken();
}
