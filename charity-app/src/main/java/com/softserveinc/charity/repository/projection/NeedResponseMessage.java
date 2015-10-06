package com.softserveinc.charity.repository.projection;

import com.softserveinc.charity.model.NeedResponse;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "message", types = {NeedResponse.class})
public interface NeedResponseMessage {
    String getDescription();
}
