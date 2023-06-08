package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.PetDTO2;
import org.frh.pets_backend.entity.Category;
import org.frh.pets_backend.entity.Pet;
import org.frh.pets_backend.entity.User;
import org.frh.pets_backend.mapper.CategoryMapperImpl;
import org.frh.pets_backend.mapper.PetMapperImpl;
import org.frh.pets_backend.mapper.UserMapperImpl;
import org.frh.pets_backend.repository.CategoryRepository;
import org.frh.pets_backend.repository.PetRepository;
import org.frh.pets_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PetServiceImpl implements PetService{
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapperImpl dtoMapperPet;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    UserMapperImpl dtoMapperUser;

    @Autowired
    CategoryMapperImpl dtoMapperCategory;


    @Override
    public List<PetDTO> listPets() {
        List<Pet> pets = petRepository.findAll();

        List<PetDTO> petDTOS = new ArrayList<>();
        for(Pet pet:pets){
            PetDTO petDTO = dtoMapperPet.fromPet(pet);
            if(pet.getUser()!=null)
            petDTO.setUser(dtoMapperUser.fromUser(pet.getUser()));
            if(pet.getCategory()!=null)
            petDTO.setCategory(dtoMapperCategory.fromCategory(pet.getCategory()));
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @Override
    public PetDTO2 savePet(PetDTO2 petDTO2) throws Exception {
        log.info("saving new pet !!!");
        System.out.println("******* pet dto :"+petDTO2);

        Pet pet = dtoMapperPet.fromPetDTO2(petDTO2);

        if(petDTO2.getUserId()!=null) {
            Optional<User> user = userRepository.findById(petDTO2.getUserId());
            if (user.isPresent())
                pet.setUser(user.get());
        }
        if(petDTO2.getCategoryId()!=null) {
            Optional<Category> category = categoryRepository.findById(petDTO2.getCategoryId());
            if (category.isPresent())
                pet.setCategory(category.get());
        }

        Pet savedPet = petRepository.save(pet);
        petDTO2 = dtoMapperPet.fromPet2(savedPet);
        if(savedPet.getUser()!=null)
            petDTO2.setUserId(dtoMapperUser.fromUser(savedPet.getUser()).getId());
        if(savedPet.getCategory()!=null)
            petDTO2.setCategoryId(dtoMapperCategory.fromCategory(savedPet.getCategory()).getId());
        return petDTO2;
    }
}
