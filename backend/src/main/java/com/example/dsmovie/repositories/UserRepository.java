package com.example.dsmovie.repositories;

import com.example.dsmovie.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
   User findByEmail(String email);
}
