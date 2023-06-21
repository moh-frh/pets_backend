package org.frh.pets_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    /*@OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
    // we add this line when we have bidirectional relation (OneToMany and ManyToMany) to avoid infinite loop
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private List<PetCharacter> petCharacters;*/

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
        updatedAt = createdAt;
    }
    @PreUpdate
    public void preUpdate(){
        updatedAt = new Date();
    }

}
