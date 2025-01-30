package com.app.showtime.repository;

import com.app.showtime.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "select mr.* from role mr where mr.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}
