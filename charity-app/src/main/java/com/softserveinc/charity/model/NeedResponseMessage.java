package com.softserveinc.charity.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "message", types = {NeedResponse.class})
public interface NeedResponseMessage {
    String getDescription();
}
