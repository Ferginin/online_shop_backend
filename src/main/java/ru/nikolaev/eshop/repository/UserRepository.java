package ru.nikolaev.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.eshop.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByUsernameAndPassword(String username, String password);
    Boolean existsUserByEmailAndPassword(String email, String password);

    User findUserByEmail(String email);
}