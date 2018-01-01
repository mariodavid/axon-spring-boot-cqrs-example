package de.diedavids.axonpolicyexample.api

import de.diedavids.axonpolicyexample.commands.CancelPolicyCommand
import de.diedavids.axonpolicyexample.commands.CreatePolicyCommand
import de.diedavids.axonpolicyexample.commands.PolicyMtaCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import java.util.concurrent.CompletableFuture


/**
 * This REST API represents the writing side of the application (sending the commands)
 */
@RestController
class PolicyCommandController {

    /**
     * the command gateway is used for sending commands through the Axon framework
     */
    @Autowired
    CommandGateway commandGateway
    

    @PostMapping("/policies")
    CompletableFuture<String> createPolicy() {
        commandGateway.send(new CreatePolicyCommand(id: randomId()))
    }


    @PutMapping("/policies/{policyId}")
    CompletableFuture<String> modifyPolicy(@PathVariable String policyId) {
        commandGateway.send(new PolicyMtaCommand(id: policyId))
    }

    @PostMapping("/policies/{policyId}/cancellation")
    CompletableFuture<String> cancelPolicy(@PathVariable String policyId, @RequestBody CancelPolicyCommand command) {
        command.id = policyId
        commandGateway.send(command)
    }


    private String randomId() {
        UUID.randomUUID().toString()
    }
}
