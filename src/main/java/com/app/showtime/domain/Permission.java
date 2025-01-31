package com.app.showtime.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "permission")
@Entity
@NoArgsConstructor
public class Permission implements Serializable {

    public static final String ADD_MOVIE = "ADD_MOVIE";
    public static final String UPDATE_MOVIE = "UPDATE_MOVIE";
    public static final String DELETE_MOVIE = "DELETE_MOVIE";
    public static final String ELEVATE_USER = "ELEVATE_USER";
    public static final String ADD_SHOWTIME = "ADD_SHOWTIME";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
    private List<RolePermission> rolePermissions;
}
