package de.diedavids.axonpolicyexample.events

class PolicyEvent implements Serializable {
    String id
}

class PolicyCreatedEvent extends PolicyEvent {}

class PolicyCancelledEvent extends PolicyEvent {
    String cancelDate
}


