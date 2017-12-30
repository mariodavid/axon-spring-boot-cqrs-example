package de.diedavids.axonpolicyexample.commands

import org.axonframework.commandhandling.TargetAggregateIdentifier

import java.time.LocalDate

class PolicyCommand implements Serializable {

    @TargetAggregateIdentifier
    String id

}
