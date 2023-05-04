package com.ddd.toy.pet.salon.ui;

import com.ddd.toy.pet.salon.application.PetService;
import com.ddd.toy.pet.salon.dto.PetRequest;
import com.ddd.toy.pet.salon.dto.PetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ShopController {
//    private ShopService shopService;
//
//    public ShopController(ShopService shopService) { this.shopService = shopService; }
//
//    @PostMapping("/pets")
//    public ResponseEntity<PetResponse> registerPet(@RequestBody PetRequest petRequest) {
//        PetResponse pet = petService.savePet(petRequest);
//        return ResponseEntity.created(URI.create("/pets/" + pet.getPetNo())).body(pet);
//    }
//
//    @GetMapping(value = "/pets", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<PetResponse>> showPets() {
//        return ResponseEntity.ok().body(petService.findAllPets());
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity handleIllegalArgsException() {
//        return ResponseEntity.badRequest().build();
//    }
}
