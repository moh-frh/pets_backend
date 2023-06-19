package org.frh.pets_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString

@Entity
public class PetCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;


    @PrePersist
    private void prePersist()
    {
        createdAt=new Date();
        updatedAt=createdAt;
    }
    @PreUpdate
    private void preUpdate()
    {
        updatedAt=new Date();
    }

}
