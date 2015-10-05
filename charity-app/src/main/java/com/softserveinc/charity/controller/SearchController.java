package com.softserveinc.charity.controller;


import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.elasticsearch.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@ExposesResourceFor(NeedDetails.class)
public class SearchController implements ResourceProcessor<RepositoryLinksResource>
{

    @Autowired
    private ElasticSearchService elasticSearchService;

    @ResponseBody
    @RequestMapping(value = "/{type}",method = RequestMethod.GET, produces = "application/hal+json")
    public FacetedPage searchNeeds(@PathVariable("type") String type,
                                   @RequestParam(value = "wildcard",required = false) boolean wildcard,
                                   @RequestParam(value = "query",required = true) String query,
                                   @RequestParam(value = "region",required = false) String region,
                                   @RequestParam(value = "city",required = false) String city,
                                   @RequestParam(value = "category",required = false) String category)
    {
        switch (type){
            case "needs":
                return elasticSearchService.findNeeds(wildcard, query, region, city, category);
            case "offers":
                return elasticSearchService.findOffers(wildcard, query, region, city, category);
            default:
                throw new ResourceNotFoundException(String.format("Resource with type {%s} was not found", type));
        }
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource
                .add(ControllerLinkBuilder.linkTo(SearchController.class)
                        .withRel("/api/search/needs"));
        resource
                .add(ControllerLinkBuilder.linkTo(SearchController.class)
                        .withRel("/api/search/offers"));
        return resource;
    }
}
