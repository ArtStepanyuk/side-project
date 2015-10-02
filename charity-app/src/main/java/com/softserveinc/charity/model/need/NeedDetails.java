package com.softserveinc.charity.model.need;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "need", shards = 1, replicas = 1)
public class NeedDetails extends BaseNeed {
    @JsonIgnore
    public void setId(Integer id){
        this.id = id;
    }
}
