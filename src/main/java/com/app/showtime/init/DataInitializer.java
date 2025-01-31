package com.app.showtime.init;

import com.app.showtime.domain.*;
import com.app.showtime.enumeration.RoleTypeEnum;
import com.app.showtime.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final SeatRepository seatRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionsRepository rolePermissionsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Initialize Roles
        Role userRole = roleRepository.findByName(RoleTypeEnum.USER.getText())
                .orElseGet(() -> {
                    Role newUserRole = new Role(RoleTypeEnum.USER);
                    return roleRepository.save(newUserRole);
                });

        Role adminRole = roleRepository.findByName(RoleTypeEnum.ADMIN.getText())
                .orElseGet(() -> {
                    Role newAdminRole = new Role(RoleTypeEnum.ADMIN);
                    return roleRepository.save(newAdminRole);
                });

        // Initialize Permissions
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();
        Permission permission3 = new Permission();
        Permission permission4 = new Permission();
        Permission permission5 = new Permission();


        permission1.setName(Permission.ADD_MOVIE);
        permission2.setName(Permission.DELETE_MOVIE);
        permission3.setName(Permission.UPDATE_MOVIE);
        permission4.setName(Permission.ELEVATE_USER);
        permission5.setName(Permission.ADD_SHOWTIME);

        permissionRepository.save(permission1);
        permissionRepository.save(permission2);
        permissionRepository.save(permission3);
        permissionRepository.save(permission4);
        permissionRepository.save(permission5);


        // Associate Role with Permissions
        RolePermission rolePermission1 = new RolePermission();
        RolePermission rolePermission2 = new RolePermission();
        RolePermission rolePermission3 = new RolePermission();
        RolePermission rolePermission4 = new RolePermission();
        RolePermission rolePermission5 = new RolePermission();


        rolePermission1.setPermission(permission1);
        rolePermission1.setRole(adminRole);

        rolePermission2.setPermission(permission2);
        rolePermission2.setRole(adminRole);

        rolePermission3.setPermission(permission3);
        rolePermission3.setRole(adminRole);

        rolePermission4.setPermission(permission4);
        rolePermission4.setRole(adminRole);

        rolePermission5.setPermission(permission5);
        rolePermission5.setRole(adminRole);

        rolePermissionsRepository.save(rolePermission1);
        rolePermissionsRepository.save(rolePermission2);
        rolePermissionsRepository.save(rolePermission3);
        rolePermissionsRepository.save(rolePermission4);
        rolePermissionsRepository.save(rolePermission5);

        // Create Theatre
        Theatre theatre = new Theatre(null, "Cinema Royale", "Downtown City", 100, null);
        theatreRepository.save(theatre);

        // Create Seats for Theatre
        for (int i = 1; i <= 50; i++) {
            Seat seat = new Seat( "S" + i,  theatre);  // Default status is AVAILABLE
            seatRepository.save(seat);
        }

        // Create Genres
        Genre genre1 = new Genre();
        Genre genre2 = new Genre();
        Genre genre3 = new Genre();
        genre1.setName("Action");
        genre2.setName("Comedy");
        genre3.setName("Drame");
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);

        Set<Genre> actionComedyGenres = new HashSet<>();
        actionComedyGenres.add(genre1);
        actionComedyGenres.add(genre2);

        // Create Movies
        Movie movie1 = new Movie();
        movie1.setTitle("Avengers: Endgame");
        movie1.setDescription("The final battle to save the universe");
        movie1.setGenres(actionComedyGenres);
        movie1.setDuration(120);
        movieRepository.save(movie1);

        //Create Dummy Users
        User user = new User();

        //Create and fill the userRoles;
        Set<UserRole> userRoles= new HashSet<>();
        UserRole userRolee = new UserRole();
        userRolee.setUser(user);
        userRolee.setRole(adminRole);
        userRoles.add(userRolee);

        //Create and fill the UserRegistry
        UserRegistry userRegistry = new UserRegistry();
        userRegistry.setUser(user);
        userRegistry.setFirstName("Giorgos");
        userRegistry.setLastName("Pronoitis");

        //Create and fill Admin User
        user.setUsername("giorgopronoiti@gmail.com");
        user.setPassword(passwordEncoder.encode("gi456793"));
        user.setUserRoles(userRoles);
        user.setRegistry(userRegistry);

        userRepository.save(user);



    }
}
