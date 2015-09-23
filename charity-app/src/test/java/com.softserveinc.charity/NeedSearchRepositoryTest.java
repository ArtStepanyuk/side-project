package com.softserveinc.charity;

import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.Need;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.repository.*;
import com.softserveinc.charity.repository.search.CategorySearchRepository;
import com.softserveinc.charity.repository.search.CitySearchRepository;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.repository.search.RegionSearchRepository;
import com.softserveinc.charity.service.SearchService;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

//@Ignore
public class NeedSearchRepositoryTest extends AbstractWebIntegrationTest {

    @Resource
    private NeedSearchRepository needSearchRepository;

    @Resource
    private CitySearchRepository citySearchRepository;
    @Resource
    private RegionSearchRepository regionSearchRepository;
    @Resource
    private CategorySearchRepository categorySearchRepository;

    @Resource
    private NeedRepository needRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private CityRepository cityRepository;

    @Resource
    private UserRepository userRepository;

    @Autowired
    SearchService searchService;

    List<Need> needs = new ArrayList<>();
    City city;

    @Before
    public void setup(){

        Category category = categoryRepository.findOne(1);
        city = cityRepository.findByRegionId(1).get(0);
        /*
        TODO : create test user in sql
         */
        User user = userRepository.findByEmail("user@gmail.com");

        Need need1 = new Need();
        need1.setActualTo(LocalDate.now());
        need1.setAddress("Address xxx.xxx 1111");
        need1.setCategory(category);
        need1.setCity(city);
        need1.setConvenientTime("1442850355478");
        need1.setDescription("Description need #1");
        need1.setName("Name needYYY #1");
        need1.setUserCreated(user);

        Need need2 = new Need();
        need2.setActualTo(LocalDate.now());
        need2.setAddress("Address xxx.xxx 2222");
        need2.setCategory(category);
        need2.setCity(city);
        need2.setConvenientTime("1442853435478");
        need2.setDescription("Description need #2");
        need2.setName("Name needXXX #2");
        need2.setUserCreated(user);

        needs.add(need1);
        needs.add(need2);

        needSearchRepository.deleteAll();
        categorySearchRepository.deleteAll();
        citySearchRepository.deleteAll();
        regionSearchRepository.deleteAll();

        needRepository.save(needs);
        needSearchRepository.save(needs);
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void find_by_name_test(){

        List<Need> needs = needRepository.findAll();
        Assert.assertNotNull(needs);
        Assert.assertThat(needs.size(), is(2));

        List<Need> needs1 = needSearchRepository.findByName("needYYY");
        Assert.assertThat(needs1.size(), is(1));
    }


    @Test
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void find_by_some_input(){
        List<Need> needs2 = searchService.findNeeds("need");
        Assert.assertNotNull(needs2);
        Assert.assertThat(needs2.size(), is(2));

        //List<Need> needs3 = searchService.findNeeds(city.getName());
        //Assert.assertNotNull(needs3);
        //Assert.assertThat(needs3.size(), is(2));
    }
}
