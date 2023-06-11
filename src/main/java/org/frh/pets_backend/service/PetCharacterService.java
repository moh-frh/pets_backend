package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.exception.PetCharacterNotFoundException;

import java.util.List;

public interface PetCharacterService {
    List<PetCharacterDTO> listPetCharacter();

    PetCharacterDTO getPetCharacterById(Long petCharacterId) throws PetCharacterNotFoundException;

    PetCharacterDTO savePetCharacter(PetCharacterDTO petCharacterDTO);

    void deletePetCharacter(Long id);

    PetCharacterDTO updatePetCharacter(PetCharacterDTO petCharacterDTO) throws PetCharacterNotFoundException;
}
