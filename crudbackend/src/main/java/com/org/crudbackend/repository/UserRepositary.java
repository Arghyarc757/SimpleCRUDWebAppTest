package com.org.crudbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.crudbackend.model.User;

public interface UserRepositary extends JpaRepository<User, Long>{
    // This interface will automatically provide CRUD operations for the User entity
    // No need to implement any methods, Spring Data JPA will handle it for you 
}