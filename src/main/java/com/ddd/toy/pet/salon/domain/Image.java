//package com.ddd.toy.pet.salon.domain;
//
//import lombok.Data;
//import org.hibernate.annotations.Parent;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "image")
//@Data
//public class Image extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "image_no", updatable = false, nullable = false)
//    private Long imageNo;
//    private String targetCd;
//    @Column(name = "target_no")
//    private Long targetNo;
//    private String url;
//
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
//    @JoinColumn(name = "target_no", referencedColumnName = "user_no", insertable = false, updatable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pet.class)
//    @JoinColumn(name = "target_id", referencedColumnName = "pet_no")
//    private Pet pet;
//
//
//
//    public Image() {
//    }
//
//}
