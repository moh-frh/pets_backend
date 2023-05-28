package org.frh.pets_backend.repository;

import org.frh.pets_backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
