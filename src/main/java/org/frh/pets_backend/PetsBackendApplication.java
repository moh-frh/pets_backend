package org.frh.pets_backend;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.frh.pets_backend.dto.PetDTO;
import org.frh.pets_backend.enums.GenderType;
import org.frh.pets_backend.service.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PetsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PetService petService){
        return args -> {
            Stream.of("pet1", "pet2", "pet3").forEach(pet->{
                PetDTO petDTO = new PetDTO();
                petDTO.setName(pet);
                petDTO.setAge((int) (1 + Math.random()*100));
                petDTO.setGender((1 + Math.random()*100)>50?GenderType.MALE:GenderType.FEMALE);
                petDTO.setDescription(pet+" description");
                petDTO.setAddress(pet+" address");
                petDTO.setCreatedAt(new Date());
                petDTO.setUpdatedAt(new Date());

                petService.savePet(petDTO);
            });
        };
    }

}
