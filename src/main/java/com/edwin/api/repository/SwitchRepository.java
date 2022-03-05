package com.edwin.api.repository;

import com.edwin.api.entity.Switch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SwitchRepository extends JpaRepository<Switch, Long> {
    Optional<Switch> findByName(String name);
    void deleteByName(String name);
}
