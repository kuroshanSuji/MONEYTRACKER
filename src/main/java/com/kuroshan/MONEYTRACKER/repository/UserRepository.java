package com.kuroshan.MONEYTRACKER.repository;

import com.kuroshan.MONEYTRACKER.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    boolean existsById(Integer id);
    Optional<User> findById(Integer id);
    User findByName(String name);
}
