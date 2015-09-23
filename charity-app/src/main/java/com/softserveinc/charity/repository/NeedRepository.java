package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedRepository extends JpaRepository<Need, Integer> {}
