package org.frh.pets_backend.repository;

import org.frh.pets_backend.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query(
            value = "select * from character c where c.id IN (SELECT character_id FROM pet_character pc WHERE pc.pet_id = ?1)",
            nativeQuery = true
    )


        // JPQL
        /*@Query("SELECT c FROM Character c WHERE c.id IN (SELECT pc.character_id FROM PetCharacter pc WHERE pc.pet_id = ?1 ) ")*/
    List<Character> findCharactersByPetId(Long petId);

}
