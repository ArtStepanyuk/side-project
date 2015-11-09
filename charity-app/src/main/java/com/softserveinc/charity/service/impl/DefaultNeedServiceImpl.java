package com.softserveinc.charity.service.impl;

import com.softserveinc.charity.repository.jpa.NeedRepository;
import com.softserveinc.charity.service.NeedService;
import com.softserveinc.charity.model.need.Need;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class DefaultNeedServiceImpl implements NeedService {

    @Autowired
    private NeedRepository repository;

    @Override
    @Transactional
    public Need save(final Need need) {
        return repository.save(need);
    }
}
