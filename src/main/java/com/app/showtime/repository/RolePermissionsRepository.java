package com.app.showtime.repository;

import com.app.showtime.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionsRepository extends JpaRepository<RolePermission,Long> {
}
