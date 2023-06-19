package org.frh.pets_backend.utils;

import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.entity.PetCharacter;
import org.frh.pets_backend.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Utils {
    CategoryService categoryService;

    public Utils(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    public Long RandomIdFromCategoryList() {
        List<CategoryDTO> listCategories = categoryService.listCategory();
        Random random = new Random();
        int size = listCategories.size();
        int randomIndex = random.nextInt(size);
        Long randomValue = listCategories.get(randomIndex).getId();
        return randomValue;
    }

    public Long[] extractIdsFromPetCharacter(List<PetCharacter> objects) {
        Long[] ids = new Long[objects.size()];

        for (int i = 0; i < objects.size(); i++) {
            Object obj = objects.get(i);
            // each object has a method called getId() to retrieve the ID
            Long id = ((PetCharacter) obj).getId();
            ids[i] = id;
        }
        return ids;
    }
}
