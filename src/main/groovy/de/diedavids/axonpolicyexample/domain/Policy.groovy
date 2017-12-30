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

@Aggregate
class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    @AggregateIdentifier
    String id;

    LocalDate coverStartDate;

    LocalDate coverEndDate = null;

    PolicyState state;

    int apartmentSize;

    @CommandHandler
    Policy(CreatePolicyCommand command) {
        AggregateLifecycle.apply(new PolicyCreatedEvent(id: command.id));
    }

    Policy() {}

    @EventSourcingHandler
    protected void on(PolicyCreatedEvent event) {
        this.id = event.id;
    }

    @CommandHandler
    protected void on(CancelPolicyCommand command) {
        markCancelled()

        AggregateLifecycle.apply(new PolicyCancelledEvent(id: id));
    }

    private void markCancelled() {
        state = PolicyState.CANCELED
    }

    @EventSourcingHandler
    protected void on(PolicyCancelledEvent event) {
        markCancelled()
    }

}
