package com.ddd.toy.pet.salon.domain.policy;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POLICY_INFO")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SRVC_CD")
public abstract class BasePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POLICY_SN", nullable = false)
    private Long policySn;

    @Column(name = "SRVC_SN")
    private Long srvcSn;

    @Column(name = "POLICY_NM", length = 50)
    private String policyNm;

    @Column(name = "SRVC_CD", insertable = false, updatable = false)
    private String srvcCd;

    @ManyToOne()
    @JoinColumn(name = "SRVC_SN", updatable = false, insertable = false)
    private SrvcInfo srvcInfo;
}
