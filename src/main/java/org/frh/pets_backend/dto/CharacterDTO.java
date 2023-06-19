package org.frh.pets_backend.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CharacterDTO {
    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

}
