package com.bebit.springbootgrpcexample.repositories;

import com.bebit.springbootgrpcexample.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
