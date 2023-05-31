package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> listCategory();

    CategoryDTO getCategoryBtId(Long categoryId) throws CategoryNotFoundException;

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException;
}
