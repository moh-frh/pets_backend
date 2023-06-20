package org.frh.pets_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.entity.Pet;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
