package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.*;
import com.ddd.toy.pet.salon.dto.PetRequest;
import com.ddd.toy.pet.salon.dto.PetResponse;
import com.ddd.toy.pet.salon.dto.ShopRequest;
import com.ddd.toy.pet.salon.dto.ShopResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ShopService {
    private ShopRepository shopRepository;
    private UserService userService;

    public ShopService(ShopRepository shopRepository, UserService userService) {
        this.shopRepository = shopRepository;
        this.userService = userService;
    }

    @Transactional
    public ShopResponse saveShop(ShopRequest shopRequest) {
        User owner = userService.findUserById(shopRequest.getUserNo());
        Shop shop = shopRepository.save(shopRequest.toShop(owner));
        return ShopResponse.from(shop);
    }

    public List<ShopResponse> findAllShops() {
        List<Shop> allShops = shopRepository.findAll();
        return allShops.stream()
                .map(ShopResponse::from)
                .collect(Collectors.toList());
    }
}
