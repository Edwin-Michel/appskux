package com.edwin.api.repository;

import com.edwin.api.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Long> {
    Optional<Widget> findByName(String name);
    void deleteByName(String name);
}
