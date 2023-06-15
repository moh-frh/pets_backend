package org.frh.pets_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.entity.Category;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.entity.User;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PetDTO {
    private Long id;
    private String name;
    private int age;
    private GenderType gender;
    private String description;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    private UserDTO user;
    private CategoryDTO category;
    private List<Character> listCharacter;
/* ---------------------------------------------- */
    private Long userId;
    private Long categoryId;
    private List<Long> listCharacterId;


}
