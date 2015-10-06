package com.softserveinc.charity.model.projection;

import com.softserveinc.charity.model.NeedResponse;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "message", types = {NeedResponse.class})
public interface NeedResponseMessage {
    String getDescription();
}
