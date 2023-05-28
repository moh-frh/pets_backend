package org.frh.pets_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Long id;
    String name;
    private Date createdAt;
    private Date updatedAt;

}
