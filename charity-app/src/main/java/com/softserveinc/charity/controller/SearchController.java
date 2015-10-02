package com.softserveinc.charity.controller;


import com.softserveinc.charity.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(value = "/needs/search",method = RequestMethod.GET, produces = "application/hal+json")
    public PersistentEntityResource searchNeeds(PersistentEntityResourceAssembler assembler)
    {
        return assembler.toFullResource(searchService.findNeeds("some"));
    }

    @ResponseBody
    @RequestMapping(value = "/offers/search",method = RequestMethod.GET, produces = "application/hal+json")
    public PersistentEntityResource searchOffers(PersistentEntityResourceAssembler assembler)
    {
        return assembler.toFullResource(searchService.findOffers("offer"));
    }

}
