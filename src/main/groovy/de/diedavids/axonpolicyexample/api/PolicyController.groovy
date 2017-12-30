package de.diedavids.axonpolicyexample.api

import de.diedavids.axonpolicyexample.commands.CancelPolicyCommand
import de.diedavids.axonpolicyexample.commands.CreatePolicyCommand
import de.diedavids.axonpolicyexample.commands.PolicyMtaCommand
import de.diedavids.axonpolicyexample.domain.Policy
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDate
import java.util.concurrent.CompletableFuture


@RestController
class PolicyController {

    @Autowired
    CommandGateway commandGateway

    @PostMapping("/policies")
    CompletableFuture<String> createPolicy() {
        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreatePolicyCommand(id: id));
    }

    @GetMapping("/policies/{policyId}")
    Policy getPolicy(@PathVariable String policyId, @RequestParam(required = false) Integer version) {
        return null;//policyService.getPolicy(policyId, version);
    }

    @PutMapping("/policies/{policyId}")
    CompletableFuture<String> modifyPolicy(@PathVariable String policyId) {
        return commandGateway.send(new PolicyMtaCommand(id: policyId))
    }

    @PostMapping("/policies/{policyId}/cancellation")
    CompletableFuture<String> cancelPolicy(@PathVariable String policyId, @RequestBody CancelPolicyRequest cancelRequest) {
        return commandGateway.send(new CancelPolicyCommand(cancelDate: cancelRequest.cancelDate, id: policyId ))
    }
}

class CancelPolicyRequest {
    String cancelDate
}
