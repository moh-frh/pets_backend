package org.frh.pets_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    // we add this line when we have bidirectional relation (OneToMany and ManyToMany) to avoid infinite loop
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @ToString.Exclude
    private List<PetCharacter> petCharacters;

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
