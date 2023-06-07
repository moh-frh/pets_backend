package org.frh.pets_backend.mapper;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.entity.Pet;
import org.frh.pets_backend.enums.GenderType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PetMapperImpl {
    public Pet fromPetDTO(PetDTO petDTO){
        Pet pet = new Pet();

        // must add user + category in pet object !!!!

        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public PetDTO fromPet(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
}
