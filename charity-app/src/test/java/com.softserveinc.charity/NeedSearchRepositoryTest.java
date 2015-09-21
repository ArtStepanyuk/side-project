package com.softserveinc.charity;

import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.Need;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.repository.CategoryRepository;
import com.softserveinc.charity.repository.CityRepository;
import com.softserveinc.charity.repository.NeedRepository;
import com.softserveinc.charity.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@Ignore
public class NeedSearchRepositoryTest extends AbstractWebIntegrationTest {

    @Resource
    private NeedRepository needRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private CityRepository cityRepository;

    @Resource
    private UserRepository userRepository;

    @Before
    public void setup(){
        //needRepository.deleteAll();
    }

    @Test
    public void test(){
        /*Need need1 = new Need();
        need1.setActualTo();
        need1.setAddress();
        need1.setCategory();
        need1.setCity();
        need1.setConvenientTime();
        need1.setDescription();
        need1.setName();
        need1.setUserCreated();*/

        Category category = categoryRepository.findOne(1);
        City city = cityRepository.findByRegionId(1).get(0);
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


        needRepository.index(need1);
        needRepository.index(need2);

        //List<Need> needs = needRepository.findByName("need");
        List<Need> needs = (List<Need>) needRepository.findAll();
        Assert.assertThat(needs.size(), is(2));
    }

}
