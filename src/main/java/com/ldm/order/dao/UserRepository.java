package com.ldm.order.dao;

import com.ldm.order.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNameAndPassword(String name, String password);
}
