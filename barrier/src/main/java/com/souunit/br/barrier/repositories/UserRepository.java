package com.souunit.br.barrier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souunit.br.barrier.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
