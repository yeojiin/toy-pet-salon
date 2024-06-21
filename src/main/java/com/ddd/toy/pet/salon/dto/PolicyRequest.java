package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.policy.BasePolicy;
import com.ddd.toy.pet.salon.domain.policy.CashBackPolicy;
import com.ddd.toy.pet.salon.domain.policy.FreePolicy;
import lombok.Data;

import java.util.Objects;

@Data
public class PolicyRequest {
    private long srvcSn;
    private String policNm;
    private String srvcCd;
    private String chargeLmtYn;
    private int accrt;

    public BasePolicy toPolicy() {
        if(srvcCd.equals("FREE")) {
           return FreePolicy.builder()
                   .srvcSn(srvcSn)
                   .policyNm(policNm)
                   .srvcCd(srvcCd)
                   .chargeLmtYn(chargeLmtYn)
                   .build();
        } else {
            return CashBackPolicy.builder()
                    .srvcSn(srvcSn)
                    .policyNm(policNm)
                    .srvcCd(srvcCd)
                    .accurRate(accrt)
                    .build();
        }

    }
}
