package com.softserveinc.charity;

import com.softserveinc.charity.model.*;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.jpa.CategoryRepository;
import com.softserveinc.charity.repository.jpa.CityRepository;
import com.softserveinc.charity.repository.jpa.NeedRepository;
import com.softserveinc.charity.repository.jpa.UserRepository;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.elasticsearch.ElasticSearchService;
import org.joda.time.LocalDate;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;

@Transactional
public class NeedSearchRepositoryTest extends AbstractWebIntegrationTest {

    @Resource
    private NeedSearchRepository needSearchRepository;

    @Resource
    private NeedRepository needRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private CityRepository cityRepository;

    @Resource
    private UserRepository userRepository;

    @Autowired
    ElasticSearchService searchService;

    List<Need> needs = new ArrayList<>();
    List<NeedDetails> needDetailses = new ArrayList<>();
    City city1;
    City city2;
    City city3;
    Category subCategory1;
    Category subCategory2;
    Category subCategory3;
    Category subCategory4;
    Pageable pageable;

    @Before
    public void setup(){

        subCategory1 = categoryRepository.findByNameAndParent("outerwear", "child's");
        subCategory2 = categoryRepository.findByNameAndParent("sweater", "child's");
        subCategory3 = categoryRepository.findByNameAndParent("PC", "technique");
        subCategory4 = categoryRepository.findByNameAndParent("etc", "root");
        city1 = cityRepository.findByRegionId(6).get(0);
        city2 = cityRepository.findByRegionId(6).get(1);
        city3 = cityRepository.findByRegionId(6).get(2);
        /*
        TODO : create test user in sql
         */
        User user = userRepository.findByEmail("user@gmail.com");

        Need need1 = new Need();
        need1.setActualTo(LocalDate.now());
        need1.setAddress("Address xxx.xxx 1111");
        need1.setCategory(subCategory1);
        need1.setCity(city1);
        need1.setConvenientTime("1442850355478");
        need1.setDescription("Description need #1");
        need1.setName("Name needYYY #1");
        need1.setUserCreated(user);

        Need need2 = new Need();
        need2.setActualTo(LocalDate.now());
        need2.setAddress("Address xxx.xxx 2222");
        need2.setCategory(subCategory1);
        need2.setCity(city2);
        need2.setConvenientTime("1442853435478");
        need2.setDescription("Description need #2");
        need2.setName("Name needXXX #2");
        need2.setUserCreated(user);

        Need need3 = new Need();
        need3.setActualTo(LocalDate.now());
        need3.setAddress("Address xxx.xxx 2222");
        need3.setCategory(subCategory2);
        need3.setCity(city2);
        need3.setConvenientTime("1442850455478");
        need3.setDescription("Description need #3");
        need3.setName("Name needZZZ #3");
        need3.setUserCreated(user);

        Need need4 = new Need();
        need4.setActualTo(LocalDate.now());
        need4.setAddress("NewYork NewYork");
        need4.setCategory(subCategory3);
        need4.setCity(city3);
        need4.setConvenientTime("1442850455478");
        need4.setDescription("PC notebook HP 2.5 GHZ 4 GB RAM");
        need4.setName("HP Pavilion");
        need4.setUserCreated(user);

        Need need5 = new Need();
        need5.setActualTo(LocalDate.now());
        need5.setAddress("Kiev 2");
        need5.setCategory(subCategory4);
        need5.setCity(city3);
        need5.setConvenientTime("1442850455478");
        need5.setDescription("Some stuff");
        need5.setName("Some stuff");
        need5.setUserCreated(user);

        needs.add(need1);
        needs.add(need2);
        needs.add(need3);
        needs.add(need4);
        needs.add(need5);

        needSearchRepository.deleteAll();

        needRepository.save(needs);

        needSearchRepository.save(
                needs
                        .stream()
                        .map(Converter::convert)
                        .collect(Collectors.toList())
        );

        pageable = new PageRequest(0 , 10);
    }

    @Test
    public void find_by_name_test() {

        List<Need> needs_ = needRepository.findAll();
        Assert.assertNotNull(needs_);
        //Assert.assertThat(needs_.size(), is(12));

        List<NeedDetails> needs1_= needSearchRepository.findByName("needYYY");
        Assert.assertThat(needs1_.size(), is(1));
    }

    @Test
    public void find_by_some_input_name(){
        FacetedPage<NeedDetails> needs_ = searchService.findNeeds(false, "need", null, null, null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(3));
    }

    @Test
    public void find_by_some_input_address(){
        FacetedPage<NeedDetails> needs_ = searchService.findNeeds(false, "Address", null, null, null, pageable);// TODO
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(3));
    }

    @Test
    public void find_by_some_input_city(){
        FacetedPage<NeedDetails> needs_ = searchService.findNeeds(false, null, null, city2.getName(), null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(2));
    }

    @Test
    public void find_by_some_input_region(){
        FacetedPage<NeedDetails> needs_
                = searchService.findNeeds(false, "need", city1.getRegion().getName(), null, null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(3));
    }

    @Test
    public void find_by_some_input_category_and_region(){
        FacetedPage<NeedDetails> needs_
                = searchService.findNeeds(false, "need", city1.getRegion().getName(), null, subCategory1.getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(2));

        needs_ = searchService.findNeeds(false, "need", city1.getRegion().getName(), null, subCategory2.getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(1));

        needs_ = searchService.findNeeds(false, "need", city1.getRegion().getName(), null, subCategory1.getParent().getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(3));
    }

    @Test
    public void find_by_category_wildcard(){
        FacetedPage<NeedDetails> needs_
                = searchService.findNeeds(false, null, null, null, null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(5));
    }

    @Test
    public void find_by_some_input_wildcard(){
        FacetedPage<NeedDetails> needs_ = searchService.findNeeds(true, "Na", null, null, null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(3));

        needs_ = searchService.findNeeds(true, "Na", null, null, subCategory1.getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(2));

        needs_ = searchService.findNeeds(true, "So", null, null, null, pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(1));

        needs_ = searchService.findNeeds(true, "So", null, null, subCategory4.getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(1));
    }

    @Test
    public void find_by_some_input_wildcard_with_city_and_category(){
        FacetedPage<NeedDetails> needs_ = searchService.findNeeds(true, "Na", null, city2.getName(), subCategory1.getName(), pageable);
        Assert.assertNotNull(needs_);
        Assert.assertNotNull(needs_.getContent());
        Assert.assertThat(needs_.getContent().size(), is(1));
    }

    @After
    public void close(){
       needSearchRepository.deleteAll();
    }
}
