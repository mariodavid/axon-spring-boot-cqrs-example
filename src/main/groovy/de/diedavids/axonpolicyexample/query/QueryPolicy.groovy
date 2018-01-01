package de.diedavids.axonpolicyexample.query

import de.diedavids.axonpolicyexample.domain.PolicyState

import javax.persistence.Entity
import javax.persistence.Id


/**
 * An example of the query side of the Policy Entity
 *
 * In this case it contains the same attributes, but the idea is to create a entity
 * for every query requirement
 */
@Entity
class QueryPolicy {

    @Id
    String id

    String coverStartDate

    String coverEndDate

    PolicyState state

    int apartmentSize

    QueryPolicy() {}

}
