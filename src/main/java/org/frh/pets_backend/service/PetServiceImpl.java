package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.entity.Pet;
import org.frh.pets_backend.mapper.PetMapperImpl;
import org.frh.pets_backend.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@Slf4j
public class PetServiceImpl implements PetService{
    private PetRepository petRepository;
    private PetMapperImpl dtoMapperPet;

    public PetServiceImpl(PetRepository petRepository, PetMapperImpl dtoMapperPet){
        this.petRepository = petRepository;
        this.dtoMapperPet = dtoMapperPet;
    }
    @Override
    public List<PetDTO> listPets() {
        List<Pet> pets = petRepository.findAll();

        List<PetDTO> petDTOS = new ArrayList<>();
        for(Pet pet:pets){
            PetDTO petDTO = dtoMapperPet.fromPet(pet);
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @Override
    public PetDTO savePet(PetDTO petDTO) {
        log.info("saving new pet !!!");

        Pet pet = dtoMapperPet.fromPetDTO(petDTO);
        Pet savedPet = petRepository.save(pet);
        return dtoMapperPet.fromPet(savedPet);
    }
}
