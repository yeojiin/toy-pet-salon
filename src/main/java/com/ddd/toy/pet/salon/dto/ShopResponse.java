package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.Pet;
import com.ddd.toy.pet.salon.domain.Shop;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShopResponse {
    private Long shopNo;
    private String name;
    private String address;
    private String telNo;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Long userNo;
    private String userName;

    public ShopResponse(Shop shop) {
        this.shopNo = shop.getShopNo();
        this.name = shop.getName();
        this.address = shop.getAddress();
        this.telNo = shop.getTelNo();
        this.createdDate = shop.getCreatedDate();
        this.modifiedDate = shop.getModifiedDate();
        this.userNo = shop.getOwnerNo();
        this.userName = shop.getOwnerName();
    }


    public static ShopResponse from(Shop shop) {
        return new ShopResponse(shop);
    }
}
