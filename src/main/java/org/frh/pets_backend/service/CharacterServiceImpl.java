package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.CharacterDTO;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.exception.CharacterNotFoundException;
import org.frh.pets_backend.mapper.CharacterMapperImpl;
import org.frh.pets_backend.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CharacterServiceImpl implements CharacterService{
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapperImpl dtoMapperCharacter;

    @Override
    public List<CharacterDTO> listCharacter(){
        List<Character> listCharacter = characterRepository.findAll();
        List<CharacterDTO> listCharacterDTO = new ArrayList<>();
        for(Character character:listCharacter){
            CharacterDTO characterDTO = dtoMapperCharacter.fromCharacter(character);
            listCharacterDTO.add(characterDTO);
        }
        return listCharacterDTO;
    }

    @Override
    public CharacterDTO getCharacterById(Long characterId) throws CharacterNotFoundException {
        Character character = characterRepository.findById(characterId).orElseThrow(()->new CharacterNotFoundException("character not found !!!!"));
        return dtoMapperCharacter.fromCharacter(character);
    }

    @Override
    public CharacterDTO saveCharacter(CharacterDTO characterDTO){
        log.info("saving new character !!!");
        Character character = dtoMapperCharacter.fromCharacterDTO(characterDTO);
        character.setCreatedAt(new Date());
        character.setUpdatedAt(new Date());
        Character savedCharacter = characterRepository.save(character);
        return dtoMapperCharacter.fromCharacter(savedCharacter);
    }

    @Override
    public void deleteCharacter(Long id){
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO updateCharacter(CharacterDTO characterDTO) throws CharacterNotFoundException {
        log.info("updating character !!!!");
        Character characterInformation = characterRepository.findById(characterDTO.getId()).orElseThrow(()->new CharacterNotFoundException("Character not found !!!"));
        Character character = dtoMapperCharacter.fromCharacterDTO(characterDTO);
        character.setCreatedAt(characterInformation.getCreatedAt());
        character.setUpdatedAt(new Date());
        Character updatedCharacter = characterRepository.save(character);
        return dtoMapperCharacter.fromCharacter(updatedCharacter);
    }
}
