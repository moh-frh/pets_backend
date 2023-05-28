package org.frh.pets_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Pet> pets;

}
