package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.id = :id AND u.type = :type")
    boolean existsByIdAndType(@Param("id") Long id, @Param("type") UserType type);

    Optional<User> findByEmailAndPassword(String email, String password);
}
