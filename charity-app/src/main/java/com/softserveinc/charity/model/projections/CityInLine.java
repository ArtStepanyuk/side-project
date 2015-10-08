package com.softserveinc.charity.model.projections;

import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.Region;
import org.springframework.data.rest.core.config.Projection;

/**
 * This project is excerpted projection in city repository.
 */
@Projection(name="CityInLine", types = {City.class})
public interface CityInLine {

    String getName();

    Region getRegion();
}
