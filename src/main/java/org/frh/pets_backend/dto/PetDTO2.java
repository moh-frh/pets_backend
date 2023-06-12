package org.frh.pets_backend.dto;

import lombok.Data;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;

@Data
public class PetDTO2 {
    private Long id;
    private String name;
    private int age;
    private GenderType gender;
    private String description;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    private Long userId;
    private Long categoryId;


}
