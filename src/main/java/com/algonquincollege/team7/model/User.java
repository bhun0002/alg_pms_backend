package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.SignupRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "organization_name")
    private String organizationName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType type;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(SignupRequest data) {
        this.email = data.email();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.organizationName = data.organizationName();
        this.phone = data.phone();
        this.type = data.type();
        this.password = data.password();
        this.status = UserStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
