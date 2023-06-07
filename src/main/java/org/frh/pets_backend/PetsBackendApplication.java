package org.frh.pets_backend;

import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.enums.GenderType;
import org.frh.pets_backend.exception.CategoryNotFoundException;
import org.frh.pets_backend.mapper.CategoryMapperImpl;
import org.frh.pets_backend.service.CategoryService;
import org.frh.pets_backend.service.PetService;
import org.frh.pets_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class PetsBackendApplication {
    @Autowired
    Utils utils;

    public static void main(String[] args) {
        SpringApplication.run(PetsBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PetService petService, CategoryService categoryService, CategoryMapperImpl dtoMapperCategory){
        return args -> {

            // save new categories
            Stream.of("category1", "category2", "category3").forEach(categ->{
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(categ);
                categoryDTO.setCreatedAt(new Date());
                categoryDTO.setUpdatedAt(new Date());
                categoryService.saveCategory(categoryDTO);
            });

            // save new pets
            Stream.of("pet1", "pet2", "pet3").forEach(pet->{
                PetDTO petDTO = new PetDTO();
                petDTO.setName(pet);
                petDTO.setAge((int) (1 + Math.random()*100));
                petDTO.setGender((1 + Math.random()*100)>50?GenderType.MALE:GenderType.FEMALE);
                petDTO.setDescription(pet+" description");
                petDTO.setAddress(pet+" address");
                petDTO.setCreatedAt(new Date());
                petDTO.setUpdatedAt(new Date());

                CategoryDTO category = null;
                List<CategoryDTO> listCategories;
                List<Long> listCategoryIds = new ArrayList<>();

                Long randomIdCategory = utils.RandomIdFromCategoryList();

                //System.out.println("----- from utils : " + randomIdCategory);

                try {
                    category = categoryService.getCategoryBtId(randomIdCategory);
                     listCategories = categoryService.listCategory();

                    listCategories.forEach(categ->{
                        listCategoryIds.add(categ.getId());
                    });
                } catch (CategoryNotFoundException e) {
                    e.printStackTrace();
                }

                petDTO.setCategory(dtoMapperCategory.fromCategoryDTO(category));

                petService.savePet(petDTO);
            });
        };
    }

}
