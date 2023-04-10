package com.ddd.toy.pet.salon.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shop")
@Data
public class Shop extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopNo", updatable = false, nullable = false)
    private Long shopNo;
    private String name;
    private String address;
    private String telNo;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")   //FK가 있는 곳이 연관관계의 주인
    private User owner;

    protected Shop() {
    }

    public Shop(String name, String address, String telNo, User owner) {
        this.name = name;
        this.address = address;
        this.telNo = telNo;
        this.owner = owner;
    }

    public Long getOwnerNo() {
        return owner.getUserNo();
    }
    public String getOwnerName() {
        return owner.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(getShopNo(), shop.getShopNo()) && Objects.equals(getName(), shop.getName()) && Objects.equals(getAddress(), shop.getAddress()) && Objects.equals(getTelNo(), shop.getTelNo()) && Objects.equals(getOwner(), shop.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShopNo(), getName(), getAddress(), getTelNo(), getOwner());
    }


}
