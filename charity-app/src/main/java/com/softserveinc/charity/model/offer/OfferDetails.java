package com.softserveinc.charity.model.offer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Elasticsearch document for offer.
 */
@Document(indexName = "offer", shards = 1, replicas = 1)
public class OfferDetails extends BaseOffer{
    @JsonIgnore
    public void setId(Integer id){
        this.id = id;
    }

    @JsonGetter
    public Integer getId(){
        return this.id;
    }
}
