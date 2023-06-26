package org.frh.pets_backend.web;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.CharacterDTO;
import org.frh.pets_backend.exception.CharacterNotFoundException;
import org.frh.pets_backend.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CharacterRestController {
    private CharacterService characterService;
    public CharacterRestController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping("/character")
    public List<CharacterDTO> listCharacters(){
        return characterService.listCharacter();
    }
    @GetMapping("/character/{id}")
    public CharacterDTO getCharacterById(@PathVariable(name = "id") Long characterId) throws CharacterNotFoundException {
        return characterService.getCharacterById(characterId);
    }

    @PostMapping("/character")
    public CharacterDTO saveCharacter(@RequestBody CharacterDTO characterDTO){
        return characterService.saveCharacter(characterDTO);
    }

    @PutMapping("/character/{id}")
    public CharacterDTO updateCharacter(@PathVariable(name = "id") Long characterId, @RequestBody CharacterDTO characterDTO) throws CharacterNotFoundException {
        characterDTO.setId(characterId);
        return characterService.updateCharacter(characterDTO);
    }

    @DeleteMapping("/character/{id}")
    public void deleteCharacter(@PathVariable(name = "id") Long characterID){
        characterService.deleteCharacter(characterID);
    }
}
