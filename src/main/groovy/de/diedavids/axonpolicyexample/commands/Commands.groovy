package de.diedavids.axonpolicyexample.commands

import org.axonframework.commandhandling.TargetAggregateIdentifier

import java.time.LocalDate

class PolicyCommand implements Serializable {

    @TargetAggregateIdentifier
    String id

}
class PolicyMtaCommand extends PolicyCommand {}
class CreatePolicyCommand extends PolicyCommand {}

class CancelPolicyCommand extends PolicyCommand {

    String cancelDate

}
