package com.ddd.toy.pet.salon.domain.policy;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;


@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CHARGE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CashBackPolicy extends BasePolicy{

    @Column(name = "ACCURT")
    private int accurRate = 0;

}
