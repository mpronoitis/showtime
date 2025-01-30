package com.app.showtime.repository;

import com.app.showtime.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select ur from UserRole ur where user.id = :userId")
    Optional<UserRole> findOneByUserId(@Param("userId") Long id);
}
