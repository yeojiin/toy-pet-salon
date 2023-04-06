package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.Pet;
import com.ddd.toy.pet.salon.domain.PetRepository;
import com.ddd.toy.pet.salon.domain.User;
import com.ddd.toy.pet.salon.domain.UserRepository;
import com.ddd.toy.pet.salon.dto.PetRequest;
import com.ddd.toy.pet.salon.dto.PetResponse;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public PetResponse savePet(PetRequest petRequest) {
        Pet pet = petRepository.save(petRequest.toPet());
        return PetResponse.from(pet);
    }

    public List<PetResponse> findAllPets() {
        List<Pet> allPets = petRepository.findAll();
        return allPets.stream()
                .map(PetResponse::from)
                .collect(Collectors.toList());
    }
}
