package org.frh.pets_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frh.pets_backend.enums.GenderType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="MY_USER")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int phone;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String city;
    private String gps;
    private Date createdAt;
    private Date updatedAt;

    /*@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pet> pets;*/

    @PrePersist
    public void prePersist(){
        updatedAt = createdAt;
    }
    @PreUpdate
    public void preUpdate(){
        updatedAt = new Date();
    }


}
