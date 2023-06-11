package org.frh.pets_backend.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CharacterDTO {
    private Long id;
    private String name;
    private int description;
    private Date createdAt;
    private Date updatedAt;

}
