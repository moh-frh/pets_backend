package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.entity.Category;
import org.frh.pets_backend.exception.CategoryNotFoundException;
import org.frh.pets_backend.mapper.CategoryMapperImpl;
import org.frh.pets_backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    private CategoryMapperImpl dtoMapperCategory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapperImpl dtoMapperCategory){
        this.categoryRepository = categoryRepository;
        this.dtoMapperCategory = dtoMapperCategory;
    }

    @Override
    public List<CategoryDTO> listCategory(){
       List<Category> listCategory = categoryRepository.findAll();
       List<CategoryDTO> listCategoryDTO = new ArrayList<>();
       for(Category category:listCategory){
           CategoryDTO categoryDTO = dtoMapperCategory.fromCategory(category);
           listCategoryDTO.add(categoryDTO);
       }
       return listCategoryDTO;
    }

    @Override
    public CategoryDTO getCategoryBtId(Long categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("category not found !!!!"));
        return dtoMapperCategory.fromCategory(category);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        log.info("saving new category !!!");
        Category category = dtoMapperCategory.fromCategoryDTO(categoryDTO);
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        Category savedCategory = categoryRepository.save(category);
        return dtoMapperCategory.fromCategory(savedCategory);
    }

    @Override
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO){
        log.info("updating category !!!!");
        Category category = dtoMapperCategory.fromCategoryDTO(categoryDTO);
        Category updatedCategory = categoryRepository.save(category);
        return dtoMapperCategory.fromCategory(updatedCategory);
    }
}
