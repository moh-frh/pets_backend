package org.frh.pets_backend.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.entity.Pet;

import java.util.Date;

@Data
public class PetCharacterDTO {
    private Long id;
//*******************************************
    private Long petId;
    private Long characterId;
//*******************************************
    private Pet pet;
    private Character character;
//*******************************************


    private Date createdAt;
    private Date updatedAt;
}
