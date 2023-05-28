package org.frh.pets_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String description;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

}
