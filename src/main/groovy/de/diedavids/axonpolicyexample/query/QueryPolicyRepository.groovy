package de.diedavids.axonpolicyexample.query

import org.springframework.data.jpa.repository.JpaRepository


/**
 * Standard JPA repository for accessing the Query Policy entity
 */
interface QueryPolicyRepository extends JpaRepository<QueryPolicy, String> {
}
