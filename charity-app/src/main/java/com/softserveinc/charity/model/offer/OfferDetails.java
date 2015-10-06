package com.softserveinc.charity.model.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "offer", shards = 1, replicas = 1)
public class OfferDetails extends BaseOffer{
    @JsonIgnore
    public void setId(Integer id){
        this.id = id;
    }
}
