package org.frh.pets_backend.mapper;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.PetDTO2;
import org.frh.pets_backend.entity.Pet;
import org.frh.pets_backend.enums.GenderType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PetMapperImpl {
    public Pet fromPetDTO(PetDTO petDTO){
        /*System.out.println("++++++++++++++++++");
        System.out.println(petDTO);
        System.out.println("++++++++++++++++++");*/
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public PetDTO fromPet(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
    // petDTO2
    public Pet fromPetDTO2(PetDTO2 petDTO2){
        /*System.out.println("++++++++++++++++++");
        System.out.println(petDTO);
        System.out.println("++++++++++++++++++");*/
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO2, pet);
        return pet;
    }

    public PetDTO2 fromPet2(Pet pet){
        PetDTO2 petDTO2 = new PetDTO2();
        BeanUtils.copyProperties(pet, petDTO2);
        return petDTO2;
    }
}
