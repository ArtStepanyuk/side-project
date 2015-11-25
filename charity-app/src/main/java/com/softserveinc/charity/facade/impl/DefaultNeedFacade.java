package com.softserveinc.charity.facade.impl;

import com.softserveinc.charity.facade.NeedFacade;
import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;
import com.softserveinc.charity.repository.jpa.CategoryRepository;
import com.softserveinc.charity.repository.jpa.CityRepository;
import com.softserveinc.charity.service.NeedService;
import com.softserveinc.charity.service.UserService;
import com.softserveinc.charity.validator.NeedValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
public class DefaultNeedFacade implements NeedFacade {

    private static final Logger LOG = Logger.getLogger(DefaultNeedFacade.class);

    public static final String CITY_BEFORE_STRING = "name\":\"";
    public static final String REGION_BEFORE_STRING = "region\":{\"id\":";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NeedService needService;

    @Autowired
    private UserService userService;

    @Override
    public Need save(final NeedRequestData requestData, final Map<String, MultipartFile> files) {

        Assert.notNull(requestData, "Request Data can not be null");
        final Need need = new Need();

        need.setName(requestData.getName());
        need.setDescription(requestData.getDescription());
        need.setAddress(requestData.getAddress());
        need.setPickup(requestData.getPickup());
        need.setConvenientTime(requestData.getConvenientTime());
        need.setActualTo(new LocalDate(requestData.getTopicality()));

        final City city = getCityFromRequest(requestData.getCity());
        need.setCity(city);

        final Category category = getCategoryFromRequest(requestData.getCategories());
        need.setCategory(category);

        need.setUserCreated(userService.getCurrentUser());

        // TODO: Check images format and extension
        NeedValidator.validate(need);
        return needService.save(need, files);
    }

    private City getCityFromRequest(final String cityString) {
        final City city = new City();

        final String citySringPost = cityString.substring(cityString.indexOf(CITY_BEFORE_STRING) + CITY_BEFORE_STRING.length());
        final String cityName = citySringPost.substring(0, citySringPost.indexOf("\""));

        final String regionString = cityString.substring(cityString.indexOf(REGION_BEFORE_STRING) + REGION_BEFORE_STRING.length());
        final String regionId = regionString.substring(0, regionString.indexOf(","));

        return cityRepository.findByNameAndRegionId(cityName, Integer.valueOf(regionId));

    }

    private Category getCategoryFromRequest(final String categoryString) {
        if (categoryString.contains(",")){
            final String[] category = categoryString.split(",");
            final int lastIndex = category.length - 1;
            return categoryRepository.findByNameAndParent(category[lastIndex],
                    StringUtils.isNotBlank(category[lastIndex - 1]) ? category[lastIndex - 1] : "root");
        }
        return categoryRepository.findByNameAndParent(categoryString, "root");
    }

}
