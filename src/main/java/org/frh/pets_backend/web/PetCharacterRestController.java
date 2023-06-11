package org.frh.pets_backend.web;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.exception.PetCharacterNotFoundException;
import org.frh.pets_backend.service.PetCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PetCharacterRestController {
    private PetCharacterService petCharacterService;
    public PetCharacterRestController(PetCharacterService petCharacterService){
        this.petCharacterService = petCharacterService;
    }

    @GetMapping("/pet-character")
    public List<PetCharacterDTO> listPetCharacters(){
        return petCharacterService.listPetCharacter();
    }
    @GetMapping("/pet-character/{id}")
    public PetCharacterDTO getPetCharacterById(@PathVariable(name = "id") Long petCharacterId) throws PetCharacterNotFoundException {
        return petCharacterService.getPetCharacterById(petCharacterId);
    }

    @PostMapping("/pet-character")
    public PetCharacterDTO savePetCharacter(@RequestBody PetCharacterDTO petCharacterDTO){
        return petCharacterService.savePetCharacter(petCharacterDTO);
    }

    @PutMapping("/pet-character/{id}")
    public PetCharacterDTO updatePetCharacter(@PathVariable(name = "id") Long petCharacterId, @RequestBody PetCharacterDTO petCharacterDTO) throws PetCharacterNotFoundException {
        petCharacterDTO.setId(petCharacterId);
        return petCharacterService.updatePetCharacter(petCharacterDTO);
    }

    @DeleteMapping("/pet-character/{id}")
    public void deletePetCharacter(@PathVariable(name = "id") Long petCharacterID){
        petCharacterService.deletePetCharacter(petCharacterID);
    }
}
