package org.frh.pets_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.enums.GenderType;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int description;

}
