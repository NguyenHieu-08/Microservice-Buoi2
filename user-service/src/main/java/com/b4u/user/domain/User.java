package com.b4u.user.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    @Column(columnDefinition = "boolean default true", name = "is_active")
    private Boolean isActive;
    @Column(columnDefinition = "boolean default false", name = "is_online")
    private Boolean isOnline;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isActive = Boolean.TRUE;
    }
}
