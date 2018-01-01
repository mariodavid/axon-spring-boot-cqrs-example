package de.diedavids.axonpolicyexample.query

import de.diedavids.axonpolicyexample.domain.Policy
import de.diedavids.axonpolicyexample.events.PolicyEvent
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.eventsourcing.EventSourcingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component


/**
 * Updates the QueryPolicy according to events to the Policy Aggregate
 */
@Component
class QueryPolicyUpdater {

    @Autowired
    QueryPolicyRepository queryPolicyRepository

    @Autowired
    @Qualifier("policyEventSourcingRepository")
    EventSourcingRepository<Policy> policyEventSourcingRepository

    @EventSourcingHandler
    void on(PolicyEvent event) {
        Policy policy = loadPolicy(event)
        savePolicy(toQueryPolicy(policy))
    }

    private Policy loadPolicy(PolicyEvent event) {
        policyEventSourcingRepository.load(event.id).wrappedAggregate.aggregateRoot
    }

    private QueryPolicy toQueryPolicy(Policy policy) {

        QueryPolicy queryPolicy = loadOrCreateQueryPolicy(policy.id)

        queryPolicy.apartmentSize = policy.apartmentSize
        queryPolicy.state = policy.state
        queryPolicy.coverStartDate = policy.coverStartDate
        queryPolicy.coverEndDate = policy.coverEndDate

        queryPolicy
    }

    private QueryPolicy savePolicy(QueryPolicy queryPolicy) {
        queryPolicyRepository.save(queryPolicy)
    }

    private QueryPolicy loadOrCreateQueryPolicy(String id) {
        queryPolicyRepository.findOne(id) ?: new QueryPolicy(id: id)
    }
}
