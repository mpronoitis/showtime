package com.app.showtime.utils;

import com.app.showtime.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> haveUsername(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username));
    }
}
