package org.frh.pets_backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private int phone;
    private GenderType gender;
    private String city;
    private String gps;
    private Date createdAt;
    private Date updatedAt;


}
