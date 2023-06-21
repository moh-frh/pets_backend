package org.frh.pets_backend;

import org.frh.pets_backend.dto.CategoryDTO;
import org.frh.pets_backend.dto.CharacterDTO;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.dto.UserDTO;
import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.enums.GenderType;
import org.frh.pets_backend.exception.CategoryNotFoundException;
import org.frh.pets_backend.exception.CharacterNotFoundException;
import org.frh.pets_backend.exception.UserNotFoundException;
import org.frh.pets_backend.mapper.CharacterMapperImpl;
import org.frh.pets_backend.service.CategoryService;
import org.frh.pets_backend.service.CharacterService;
import org.frh.pets_backend.service.PetService;
import org.frh.pets_backend.service.UserService;
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
    CommandLineRunner commandLineRunner(CharacterMapperImpl dtoMapperCharacter, UserService userService, PetService petService, CategoryService categoryService, CharacterService characterService){
        return args -> {

            // save new users
            Stream.of("user1", "user2", "user3").forEach(user->{
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(user);
                userDTO.setCreatedAt(new Date());
                userDTO.setUpdatedAt(new Date());
                userService.saveUser(userDTO);
            });

            // save new categories
            Stream.of("category1", "category2", "category3").forEach(categ->{
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(categ);
                categoryDTO.setCreatedAt(new Date());
                categoryDTO.setUpdatedAt(new Date());
                categoryService.saveCategory(categoryDTO);
            });

            // save new character
            Stream.of("character1", "character2", "character3").forEach(character->{
                CharacterDTO characterDTO = new CharacterDTO();
                characterDTO.setName(character);
                characterDTO.setDescription(character+" description");
                characterDTO.setCreatedAt(new Date());
                characterDTO.setUpdatedAt(new Date());
                characterService.saveCharacter(characterDTO);
            });

            // save new pets
            /*Stream.of("pet1", "pet2", "pet3").forEach(pet->{
                PetDTO petDTO = new PetDTO();
                petDTO.setName(pet);
                petDTO.setAge((int) (1 + Math.random()*100));
                petDTO.setGender((1 + Math.random()*100)>50?GenderType.MALE:GenderType.FEMALE);
                petDTO.setDescription(pet+" description");
                petDTO.setAddress(pet+" address");
                petDTO.setCreatedAt(new Date());
                petDTO.setUpdatedAt(new Date());

                CategoryDTO categoryDTO = null;
                UserDTO userDTO = null;
                CharacterDTO characterDTO = null;

                try {
                    userDTO = userService.getUserById((long) 1L);
                    categoryDTO = categoryService.getCategoryBtId(1L);
                    characterDTO = characterService.getCharacterById(1L);
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                } catch (CategoryNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (CharacterNotFoundException e) {
                    throw new RuntimeException(e);
                }

                System.err.println("userDTO : "+userDTO);
                System.err.println("categoryDTO : "+categoryDTO);
                System.err.println("characterDTO : "+characterDTO);

                List<Character> listCharacter = new ArrayList<>();
                Character c = dtoMapperCharacter.fromCharacterDTO(characterDTO);

                listCharacter.add(c);

                System.err.println("listCharacter : "+listCharacter);

                petDTO.setUser(userDTO);
                petDTO.setCategory(categoryDTO);
                petDTO.setListCharacter(listCharacter);

                //********************************************
                List<Long> listC = new ArrayList<>();
                listC.add(1L);
                listC.add(2L);


                petDTO.setUserId(1L);
                petDTO.setCategoryId(1L);
                petDTO.setListCharacterId(listC);
                //********************************************

                System.err.println("petDTO : "+petDTO);

                try {
                    petService.savePet(petDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });*/
        };
    }

}
