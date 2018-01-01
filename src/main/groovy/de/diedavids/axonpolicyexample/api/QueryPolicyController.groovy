package de.diedavids.axonpolicyexample.api

import de.diedavids.axonpolicyexample.query.QueryPolicy
import de.diedavids.axonpolicyexample.query.QueryPolicyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * One Query side of the application, allowing to get a policy query representation
 */
@RestController
class QueryPolicyController {

    @Autowired
    QueryPolicyRepository queryPolicyRepository


    @GetMapping("/policies/{policyId}")
    QueryPolicy getPolicy(@PathVariable String policyId) {
        return queryPolicyRepository.findOne(policyId)
    }
}
