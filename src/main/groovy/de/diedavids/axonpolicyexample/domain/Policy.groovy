package de.diedavids.axonpolicyexample.domain


import de.diedavids.axonpolicyexample.commands.CancelPolicyCommand
import de.diedavids.axonpolicyexample.commands.CreatePolicyCommand
import de.diedavids.axonpolicyexample.events.PolicyCancelledEvent
import de.diedavids.axonpolicyexample.events.PolicyCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

import java.time.LocalDate

/**
 * The policy (POJO) is the DDD Aggregate
 *
 * Additionally to the attributes, CommandHandler & EventHandler are registered here
 */
@Aggregate
class Policy implements Serializable {

    private static final long serialVersionUID = 1L

    @AggregateIdentifier
    String id

    String coverStartDate

    String coverEndDate = null

    PolicyState state

    int apartmentSize


    Policy() {}

    @CommandHandler
    Policy(CreatePolicyCommand command) {
        AggregateLifecycle.apply(new PolicyCreatedEvent(id: command.id))
    }


    @CommandHandler
    protected void on(CancelPolicyCommand command) {
        AggregateLifecycle.apply(new PolicyCancelledEvent(id: id, cancelDate: command.cancelDate))
    }


    @EventSourcingHandler
    protected void on(PolicyCreatedEvent event) {
        this.id = event.id
        this.state = PolicyState.ACTIVE
        this.coverStartDate = LocalDate.now().toString()
        this.coverEndDate = LocalDate.now().plusYears(1).toString()
    }

    @EventSourcingHandler
    protected void on(PolicyCancelledEvent event) {
        markCancelled()
        this.coverEndDate = event.cancelDate
    }


    private void markCancelled() {
        state = PolicyState.CANCELED
    }

}
