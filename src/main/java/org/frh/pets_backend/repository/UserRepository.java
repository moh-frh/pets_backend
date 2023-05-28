package org.frh.pets_backend.repository;

import org.frh.pets_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
