package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.PetCharacterDTO;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.PetDTO2;
import org.frh.pets_backend.entity.*;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.exception.CharacterNotFoundException;
import org.frh.pets_backend.mapper.CategoryMapperImpl;
import org.frh.pets_backend.mapper.PetMapperImpl;
import org.frh.pets_backend.mapper.UserMapperImpl;
import org.frh.pets_backend.repository.*;
import org.frh.pets_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class PetServiceImpl implements PetService{
    @Autowired
    Utils utils;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapperImpl dtoMapperPet;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PetCharacterRepository petCharacterRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    UserMapperImpl dtoMapperUser;

    @Autowired
    CategoryMapperImpl dtoMapperCategory;


    @Override
    public List<PetDTO> listPets() {
        List<Pet> pets = petRepository.findAll();
        List<PetDTO> petDTOS = new ArrayList<>();



        /*List<PetCharacter> petCharacterList = petCharacterRepository.findAll();

        System.out.println("petCharacterList++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(petCharacterList);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");*/

        List<Character> charactersList = characterRepository.findCharactersByPetId(1L);

        System.err.println("pet character List++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.err.println(charactersList);
        System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for(Pet pet:pets){
            PetDTO petDTO = dtoMapperPet.fromPet(pet);
            if(pet.getUser()!=null)
                petDTO.setUser(dtoMapperUser.fromUser(pet.getUser()));
            if(pet.getCategory()!=null)
                petDTO.setCategory(dtoMapperCategory.fromCategory(pet.getCategory()));

            // get pet-character from petCharacter entity
            if(pet.getPetCharacters()!=null && pet.getPetCharacters().size()>0)
            {
                System.out.println("PetCharacters : "+pet.getPetCharacters());

                petDTO.setListCharacter(charactersList);
            }

            petDTOS.add(petDTO);
        }
        System.err.println("Azul*****************************************************************"+petDTOS);
        return petDTOS;
    }

    @Override
    public PetDTO savePet(PetDTO petDTO) throws Exception {
        log.info("saving new pet !!!");

        Pet pet = dtoMapperPet.fromPetDTO(petDTO);

        System.out.println("pet DTO :**************************************");
        System.out.println(petDTO);
        System.out.println("*******************************************");


        if(petDTO.getUserId()!=null) {
            Optional<User> user = userRepository.findById(petDTO.getUserId());
            if (user.isPresent())
                pet.setUser(user.get());
        }
        if(petDTO.getCategoryId()!=null) {
            Optional<Category> category = categoryRepository.findById(petDTO.getCategoryId());
            if (category.isPresent())
                pet.setCategory(category.get());
        }

        System.out.println("--------------------------------------------");
        System.out.println(pet);
        System.out.println("--------------------------------------------");

        Pet savedPet = petRepository.save(pet);
        System.out.println("saved pet : "+savedPet);


List<Character> listCharacters = new ArrayList<>();

// insert in pet-character
        for(Long id:petDTO.getListCharacterId()){
            PetCharacter pc = new PetCharacter();
            pc.setPet(savedPet);
            Character character = characterRepository.findById(id).orElseThrow(()->new CharacterNotFoundException("character not found !!!!"));

            // insert in list-character-dto
            listCharacters.add(character);
            // end-block ******************

            pc.setCharacter(character);
            PetCharacter savedPetCharacter = petCharacterRepository.save(pc);
            System.out.println("pet: "+savedPet.getId()+" - character : "+id);
            System.out.println("savedPetCharacter: "+savedPetCharacter);
        }
        System.out.println("listCharacters : "+listCharacters);
        petDTO.setListCharacter(listCharacters);
//end-block *************************

        petDTO.setUser(dtoMapperUser.fromUser(savedPet.getUser()));
        petDTO.setCategory(dtoMapperCategory.fromCategory(savedPet.getCategory()));

        return petDTO;
    }

    @Override
    public PetDTO2 updatePet(PetDTO2 petDTO2) throws Exception {
        log.info("updating pet !!!");

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

    @Override
    public void deletePet(Long id){
        petRepository.deleteById(id);
    }
}
