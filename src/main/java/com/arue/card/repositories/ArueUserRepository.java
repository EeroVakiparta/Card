package com.arue.card.repositories;

import com.arue.card.model.ArueUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArueUserRepository extends JpaRepository<ArueUser, Long> {
}
