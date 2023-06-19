package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.entity.Pet;
import org.frh.pets_backend.entity.PetCharacter;
import org.frh.pets_backend.exception.PetCharacterNotFoundException;
import org.frh.pets_backend.mapper.PetCharacterMapperImpl;
import org.frh.pets_backend.repository.CharacterRepository;
import org.frh.pets_backend.repository.PetCharacterRepository;
import org.frh.pets_backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PetCharacterServiceImpl implements PetCharacterService{

    @Autowired
    private PetCharacterRepository petCharacterRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CharacterRepository characterRepository;
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
        Pet pet = petRepository.findById(petCharacterDTO.getPetId()).orElse(null);
        Character character = characterRepository.findById(petCharacterDTO.getCharacterId()).orElse(null);

        petCharacter.setPet(pet);
        petCharacter.setCharacter(character);


        System.out.println("petCharacterDTO: "+petCharacterDTO);
        System.out.println("petCharacter: "+petCharacter);
        System.out.println("petCharacter after date insert: "+petCharacter);
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
