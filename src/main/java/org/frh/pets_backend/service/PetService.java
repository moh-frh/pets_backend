package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.PetDTO2;
import org.frh.pets_backend.exception.PetException;
import org.frh.pets_backend.exception.UserNotFoundException;

import java.util.List;

public interface PetService {
    List<PetDTO> listPets() ;

    PetDTO savePet(PetDTO petDTO) throws Exception;

    PetDTO2 updatePet(PetDTO2 petDTO2) throws Exception;

    void deletePet(Long id);
}
