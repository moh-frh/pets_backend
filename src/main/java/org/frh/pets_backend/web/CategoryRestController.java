package org.frh.pets_backend.web;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.exception.CategoryNotFoundException;
import org.frh.pets_backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryRestController {
    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> listCategories(){
        return categoryService.listCategory();
    }

    @GetMapping("/categories/{id}")
    public CategoryDTO getCategoryById(@PathVariable(name="id") Long categoryId) throws CategoryNotFoundException {
        return categoryService.getCategoryBtId(categoryId);
    }

    @PostMapping("/categories")
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable(name="id") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable(name = "id") Long categoryId, @RequestBody CategoryDTO categoryDTO) throws CategoryNotFoundException {
        categoryDTO.setId(categoryId);
        return categoryService.updateCategory(categoryDTO);
    }

}
