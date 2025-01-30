package com.app.showtime.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role_permissions")
public class RolePermission {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;
}
