package org.frh.pets_backend.repository;

import org.frh.pets_backend.entity.PetCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetCharacterRepository extends JpaRepository<PetCharacter, Long> {
}
