package com.edwin.api.repository;

import com.edwin.api.entity.Thermometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThermometerRepository extends JpaRepository<Thermometer, Long> {
    List<Thermometer> findByName(String name);
    void deleteByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM thermometer WHERE name=:name ORDER BY id DESC LIMIT 1;")
    Optional<Thermometer> findByIdLast(@Param("name") String name);
    //SELECT * FROM thermometer WHERE name='widget' ORDER BY id DESC LIMIT 1;
}