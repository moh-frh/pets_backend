package org.frh.pets_backend.repository;

import org.frh.pets_backend.entity.Character;
import org.frh.pets_backend.entity.PetCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetCharacterRepository extends JpaRepository<PetCharacter, Long> {
    //	native query
	/*@Query(
			value = "select * from character c where c.id IN (SELECT character_id FROM petCharacter pc WHERE pc.pet_id = ?1) ",
			nativeQuery = true
	)*/


}
