package com.example.demo.repository;

import com.example.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByUserId(Long user);
    List<Todo> findAllByUserId(Long user);
}
