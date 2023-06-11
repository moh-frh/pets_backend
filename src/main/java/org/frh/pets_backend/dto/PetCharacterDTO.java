package org.frh.pets_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetCharacterDTO {
    private Long id;
    String name;
    String description;
    private Date createdAt;
    private Date updatedAt;
}
