package de.diedavids.axonpolicyexample.config

import de.diedavids.axonpolicyexample.domain.Policy
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventsourcing.eventstore.EventStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * generally most plumbing is done through the spring-boot-axon-starter.
 *
 */
@Configuration
class AxonConfig {

    /**
     * Additionally a repository will be defined for accessing the Policy Aggregate
     */
    @Bean
    EventSourcingRepository<Policy> policyEventSourcingRepository(EventStore eventStore) {
        EventSourcingRepository<Policy> repository = new EventSourcingRepository(Policy, eventStore)
        return repository
    }



}
