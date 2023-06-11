package org.frh.pets_backend.mapper;

import org.frh.pets_backend.dto.CharacterDTO;
import org.frh.pets_backend.entity.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CharacterMapperImpl {
    public Character fromCharacterDTO(CharacterDTO characterDTO){
        Character character = new Character();
        BeanUtils.copyProperties(characterDTO, character);
        return character;
    }

    public CharacterDTO fromCharacter(Character character){
        CharacterDTO characterDTO = new CharacterDTO();
        BeanUtils.copyProperties(character, characterDTO);
        return characterDTO;
    }
}
