package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.policy.BasePolicy;
import com.ddd.toy.pet.salon.domain.policy.CashBackPolicy;
import com.ddd.toy.pet.salon.domain.policy.CashBackRepository;
import com.ddd.toy.pet.salon.domain.policy.PolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PolicyService {

    private PolicyRepository policyRepository;
    private CashBackRepository cashBackRepository;

    public PolicyService(PolicyRepository policyRepository, CashBackRepository cashBackRepository) {
        this.policyRepository = policyRepository;
        this.cashBackRepository = cashBackRepository;
    }

    @Transactional
    public BasePolicy regPolicy(BasePolicy policy) {
        return policyRepository.save(policy);
    }

    public void findAllPolicy() {

        List<BasePolicy> allPolicy = policyRepository.findAll();

        List<CashBackPolicy> cashbackPolicy = cashBackRepository.findAll();
    }
}
