package com.app.showtime.dto;

import com.app.showtime.enumeration.RoleTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;
    private RoleTypeEnum name;
}
