package com.ddd.toy.pet.salon.domain.policy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("FREE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FreePolicy extends BasePolicy{

    @Column(name = "POLICY_OPER_BGNG_DT")
    private LocalDateTime policyOperBgngDt;

    @Column(name = "POLICY_OPER_END_DT")
    private LocalDateTime policyOperEndDt;

    @Column(name = "CHARGE_LMT_YN")
    private String chargeLmtYn;

}
