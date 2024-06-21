package com.ddd.toy.pet.salon.ui;

import com.ddd.toy.pet.salon.application.PolicyService;
import com.ddd.toy.pet.salon.domain.policy.BasePolicy;
import com.ddd.toy.pet.salon.dto.PolicyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PolicyController {

    private PolicyService policyService;

    public PolicyController(PolicyService policyService) { this.policyService = policyService; }

    @PostMapping("/policys")
    public void registerPolicy(@RequestBody PolicyRequest policyRequest) {


        BasePolicy policy = policyService.regPolicy(policyRequest.toPolicy());

    }


    @GetMapping("/all")
    public void findPolicy() {

        policyService.findAllPolicy();

    }
}
