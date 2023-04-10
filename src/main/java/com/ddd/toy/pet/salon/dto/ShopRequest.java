package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.Shop;
import com.ddd.toy.pet.salon.domain.User;
import lombok.Data;

@Data
public class ShopRequest {
    private String name;
    private String address;
    private String telNo;
    private Long userNo;

    protected ShopRequest() {
    }

    public Shop toShop(User owner) {
        return new Shop(name, address, telNo, owner);
    }
}
