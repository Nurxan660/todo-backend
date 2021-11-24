package com.example.demo.repository;

import com.example.demo.entity.EnumRole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByNickname(String nickname);
Boolean existsByNickname(String nickname);
List<User> findAllByRolesName(EnumRole name);

}
