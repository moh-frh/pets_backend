package org.frh.pets_backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.entity.Category;
import org.frh.pets_backend.entity.User;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;

@Data
public class PetDTO {
    private Long id;
    private String name;
    private int age;
    private GenderType gender;
    private String description;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    private User user;
    private Category category;

}
