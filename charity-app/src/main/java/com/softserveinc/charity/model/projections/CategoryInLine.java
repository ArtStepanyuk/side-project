package com.softserveinc.charity.model.projections;
import com.softserveinc.charity.model.Category;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "InLine", types = {Category.class})
public interface CategoryInLine {
    Integer getId();
    Category getParent();
    List<Category> getChildren();
    String getName();
}