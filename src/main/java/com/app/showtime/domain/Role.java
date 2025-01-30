package com.app.showtime.domain;

import com.app.showtime.enumeration.RoleTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<RolePermission> rolePermissions;

  public Role(RoleTypeEnum roleTypeEnum) {
      this.name = roleTypeEnum;
  }
}
