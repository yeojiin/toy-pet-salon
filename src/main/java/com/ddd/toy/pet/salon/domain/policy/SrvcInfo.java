package com.ddd.toy.pet.salon.domain.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "SRVC_INFO")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SrvcInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SRVC_SN", nullable = false)
    private long srvcSn;

    @Column
    private String srvcNm;

}
