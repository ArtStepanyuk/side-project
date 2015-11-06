package com.softserveinc.charity.model.need;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * JPA entity for need.
 */
@Entity
@Table(name = "needs")
public class Need extends BaseNeed {
}
