package com.softserveinc.charity.elasticsearch;

import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.model.offer.OfferDetails;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.jpa.NeedRepository;
import com.softserveinc.charity.repository.jpa.OfferRepository;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ElasticSearchInitializer {

    @Autowired
    NeedSearchRepository needSearchRepository;

    @Autowired
    OfferSearchRepository offerSearchRepository;

    @Autowired
    NeedRepository needRepository;

    @Autowired
    OfferRepository offerRepository;

    public void init(){
        needSearchRepository.deleteAll();
        offerSearchRepository.deleteAll();

        List<NeedDetails> needs = needRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());

        needSearchRepository.save(needs);

        List<OfferDetails> offers = offerRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());

        offerSearchRepository.save(offers);
    }
}
