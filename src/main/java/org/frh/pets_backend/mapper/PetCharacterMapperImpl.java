package org.frh.pets_backend.mapper;

import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.entity.PetCharacter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PetCharacterMapperImpl {

    public  PetCharacter fromPetCharacterDTO(PetCharacterDTO petCharacterDTO){
        PetCharacter petCharacter = new PetCharacter();
        BeanUtils.copyProperties(petCharacterDTO, petCharacter);
        return petCharacter;
    }

    public PetCharacterDTO fromPetCharacter(PetCharacter petCharacter){
        PetCharacterDTO petCharacterDTO = new PetCharacterDTO();
        BeanUtils.copyProperties(petCharacter, petCharacterDTO);
        return petCharacterDTO;
    }

}
