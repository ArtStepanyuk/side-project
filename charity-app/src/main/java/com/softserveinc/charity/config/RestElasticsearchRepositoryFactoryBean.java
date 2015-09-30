package com.softserveinc.charity.config;


@SuppressWarnings("rawtypes")
public class RestElasticsearchRepositoryFactoryBean
        extends org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactoryBean {
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() {
        setMappingContext(new org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext());
        super.afterPropertiesSet();
    }
}
