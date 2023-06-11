package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.CharacterDTO;
import org.frh.pets_backend.exception.CharacterNotFoundException;

import java.util.List;

public interface CharacterService {
    List<CharacterDTO> listCharacter();

    CharacterDTO getCharacterById(Long characterId) throws CharacterNotFoundException;

    CharacterDTO saveCharacter(CharacterDTO characterDTO);

    void deleteCharacter(Long id);

    CharacterDTO updateCharacter(CharacterDTO characterDTO) throws CharacterNotFoundException;
}
