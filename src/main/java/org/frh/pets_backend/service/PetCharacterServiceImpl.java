package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.entity.PetCharacter;
import org.frh.pets_backend.exception.PetCharacterNotFoundException;
import org.frh.pets_backend.mapper.PetCharacterMapperImpl;
import org.frh.pets_backend.repository.PetCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PetCharacterServiceImpl implements PetCharacterService{

    @Autowired
    private PetCharacterRepository petCharacterRepository;
    @Autowired
    private PetCharacterMapperImpl dtoMapperPetCharacter;

    @Override
    public List<PetCharacterDTO> listPetCharacter(){
        List<PetCharacter> listPetCharacter = petCharacterRepository.findAll();
        List<PetCharacterDTO> listPetCharacterDTO = new ArrayList<>();
        for(PetCharacter petCharacter:listPetCharacter){
            PetCharacterDTO petCharacterDTO = dtoMapperPetCharacter.fromPetCharacter(petCharacter);
            listPetCharacterDTO.add(petCharacterDTO);
        }
        return listPetCharacterDTO;
    }

    @Override
    public PetCharacterDTO getPetCharacterById(Long petCharacterId) throws PetCharacterNotFoundException {
        PetCharacter petCharacter = petCharacterRepository.findById(petCharacterId).orElseThrow(()->new PetCharacterNotFoundException("character not found !!!!"));
        return dtoMapperPetCharacter.fromPetCharacter(petCharacter);
    }

    @Override
    public PetCharacterDTO savePetCharacter(PetCharacterDTO petCharacterDTO){
        log.info("saving new pet-character !!!");
        PetCharacter petCharacter = dtoMapperPetCharacter.fromPetCharacterDTO(petCharacterDTO);
        petCharacter.setCreatedAt(new Date());
        petCharacter.setUpdatedAt(new Date());
        PetCharacter savedPetCharacter = petCharacterRepository.save(petCharacter);
        return dtoMapperPetCharacter.fromPetCharacter(savedPetCharacter);
    }

    @Override
    public void deletePetCharacter(Long id){
        petCharacterRepository.deleteById(id);
    }

    @Override
    public PetCharacterDTO updatePetCharacter(PetCharacterDTO petCharacterDTO) throws PetCharacterNotFoundException {
        log.info("updating pet-character !!!!");
        PetCharacter petCharacterInformation = petCharacterRepository.findById(petCharacterDTO.getId()).orElseThrow(()->new PetCharacterNotFoundException("Character not found !!!"));
        PetCharacter petCharacter = dtoMapperPetCharacter.fromPetCharacterDTO(petCharacterDTO);
        petCharacter.setCreatedAt(petCharacterInformation.getCreatedAt());
        petCharacter.setUpdatedAt(new Date());
        PetCharacter updatedPetCharacter = petCharacterRepository.save(petCharacter);
        return dtoMapperPetCharacter.fromPetCharacter(updatedPetCharacter);
    }

}
