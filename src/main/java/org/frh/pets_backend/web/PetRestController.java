package org.frh.pets_backend.web;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.PetDTO2;
import org.frh.pets_backend.exception.PetException;
import org.frh.pets_backend.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PetRestController {
    private PetService petService;
    public PetRestController(PetService petService){
        this.petService = petService;
    }

    @GetMapping("/pets")
    public List<PetDTO> listPets() {
        return petService.listPets();
    }

    @PostMapping("/pets")
    public PetDTO2 savePet(@RequestBody PetDTO2 petDTO2) throws Exception {
        System.out.println("::controller::save_user");
        return petService.savePet(petDTO2);
    }

}
