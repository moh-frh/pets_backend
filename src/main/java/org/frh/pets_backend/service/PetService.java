package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.exception.PetException;

import java.util.List;

public interface PetService {
    List<PetDTO> listPets() ;

    PetDTO savePet(PetDTO petDTO);
}
