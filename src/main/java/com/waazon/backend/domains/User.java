package com.waazon.backend.domains;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long uId;

    @NotEmpty
    @Size(min = 3, max = 50, message = "username size")
    String username;

    @NotEmpty
    @Size(min = 3, max = 500, message = "password size")
    String password;

    @NotEmpty
    @Size(min = 3, max = 50, message = "first name size")
    String fName;

    @NotEmpty
    @Size(min = 3, max = 50, message = "last name size")
    String lName;

    @Email
    String email;

    @OneToOne(cascade = CascadeType.ALL)
    Phone phone;

    private String image;

    public void addRole(Role role) {
        roles.add(role);
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>();
}
