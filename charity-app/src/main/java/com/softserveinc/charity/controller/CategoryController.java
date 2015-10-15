package com.softserveinc.charity.controller;

import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.repository.jpa.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Gets root category with all children nodes.
 */
@RestController
@RequestMapping(value = "/api/category")
@ExposesResourceFor(Category.class)
public class CategoryController extends AbstractController implements
        ResourceProcessor<RepositoryLinksResource> {

    private static final Logger LOG = LoggerFactory.getLogger("com.softservinc.charity");

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/root", method = RequestMethod.GET)
    public Category getRoot() {
        return categoryRepository.getRoot();
	}

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource
                .add(ControllerLinkBuilder.linkTo(CategoryController.class)
                        .withRel("/api/category/root"));
        return resource;
    }
}
